package com.example.loginfinal.CustomerUi

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.loginfinal.R
import com.example.loginfinal.auth.authViewModel
import kotlinx.coroutines.time.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.toJavaDuration


@Composable
fun service(modifier: Modifier=Modifier, navController: NavController, authViewModel: authViewModel, onSchedule:  (String, String) -> Unit) {
    var servicedDate by remember { mutableStateOf(TextFieldValue("")) }
    var dueDate by remember { mutableStateOf(TextFieldValue("")) }
    var countdown by remember { mutableStateOf(0L) }
    var isScheduled by remember { mutableStateOf(false) }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        val (savedDueDate, savedRemainingTime) = loadCountdown(context)
        if (savedDueDate != null && savedRemainingTime > 0) {
            dueDate = TextFieldValue(savedDueDate)
            countdown = savedRemainingTime / 1000 // Convert to seconds
            isScheduled = true
        }
    }

    LaunchedEffect(isScheduled) {
        if (isScheduled) {
            while (countdown > 0) {
                delay(1000.milliseconds.toJavaDuration()) // wait for 1 second
                countdown -= 1
            }
        }
    }


    Column(modifier = Modifier.padding(16.dp)) {
        if (!isScheduled) {
        OutlinedTextField(
            value = servicedDate,
            onValueChange = { servicedDate = it },
            label = { Text("Insured Date (yyyy-MM-dd)") }
        )
        OutlinedTextField(
            value = dueDate,
            onValueChange = { dueDate = it },
            label = { Text("Due Date (yyyy-MM-dd)") }
        )
        Button(
            onClick = {
                if (servicedDate.text.isNotBlank() && dueDate.text.isNotBlank()) {
                    try {
                        val delay = calculateDelay(dueDate.text)
                        if (delay > 0) {
                            onSchedule(servicedDate.text, dueDate.text)
                            saveCountdown(context, dueDate.text, delay)
                            countdown = delay / 1000 // Start countdown in seconds
                            scheduleServiceDueWorker(context, dueDate.text)
                        } else {
                            Toast.makeText(context, "The due date is already past.", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Please fill in both fields.", Toast.LENGTH_SHORT).show()
                }
                      },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Schedule Notification")
        } }
        else {
            val days = countdown / 86400 // 86400 seconds in a day
            val hours = (countdown % 86400) / 3600 // 3600 seconds in an hour
            val minutes = (countdown % 3600) / 60 // 60 seconds in a minute
            val seconds = countdown % 60 // remaining seconds

            Text(
                text = "Countdown: $days days, $hours hours, $minutes minutes, $seconds seconds",
                modifier = Modifier.padding(top = 16.dp)
            )

        }
        }
}

class serviceDueWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val dueDate = inputData.getString("dueDate") ?: return Result.failure()

        if (isDue(dueDate)) {
            sendNotification("Insurance Due", "Your insurance is due today!")
        }

        return Result.success()
    }

    private fun isDue(dueDate: String): Boolean {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val today = formatter.format(Date())
        return today == dueDate
    }

    private fun sendNotification(title: String, message: String) {
        val notificationManager = applicationContext.getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "insurance_due_channel",
                "Insurance Due Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for insurance due notifications"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, "insurance_due_channel")
            .setSmallIcon(R.drawable.service) // Replace with your icon
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }
}



fun scheduleServiceDueWorker(context: Context, dueDate: String) {
    val data = Data.Builder()
        .putString("dueDate", dueDate)
        .build()

    val workRequest = OneTimeWorkRequestBuilder<InsuranceDueWorker>()
        .setInputData(data)
        .setInitialDelay(calculateDelay(dueDate), TimeUnit.MILLISECONDS)
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)
}

    fun calculateDelay(dueDate: String): Long {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dueDateTime = formatter.parse(dueDate)
    val now = Date()
    return if (dueDateTime != null && dueDateTime.after(now)) {
        dueDateTime.time - now.time
    } else {
        0L // Already due
    }
}
fun saveCountdown(context: Context, dueDate: String, remainingTime: Long) {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    with(prefs.edit()) {
        putString("due_date", dueDate)
        putLong("remaining_time", remainingTime)
        apply()
    }
}

fun loadCountdown(context: Context): Pair<String?, Long> {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val dueDate = prefs.getString("due_date", null)
    val remainingTime = prefs.getLong("remaining_time", 0L)
    return Pair(dueDate, remainingTime)
}
/*@Composable
fun startCountdown(dueDate: String, onCountdownUpdate: (Long) -> Unit) {
    val delay = calculateDelay(dueDate)
    var countdown = delay / 1000 // convert to seconds
    LaunchedEffect(Unit) {
        while (countdown > 0) {
            delay(1000.milliseconds.toJavaDuration()) // wait for 1 second
            countdown -= 1
            onCountdownUpdate(countdown)
        }
    }
}*/


