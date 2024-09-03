package com.example.loginfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.loginfinal.auth.MyNavigation
import com.example.loginfinal.auth.authViewModel
import com.example.loginfinal.ui.theme.LoginFinalTheme
import com.mapbox.common.MapboxOptions


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapboxOptions.accessToken = getString(R.string.mapbox_access_token)
        enableEdgeToEdge()
        val authViewModel: authViewModel by viewModels()
        setContent {

            LoginFinalTheme {
                Scaffold (modifier = Modifier.fillMaxSize()){innerPaddig ->
                    MyNavigation(modifier = Modifier.padding(innerPaddig), authViewModel = authViewModel)

                }
            }
            }
        }
    }


/*@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        // Your app's theme can go here
        content()
    }
}

@Composable
fun InsuranceTest() {
    val context= LocalContext.current
    insurence{ insuredDate, dueDate ->
        Log.d("InsuranceTest", "Insured Date: $insuredDate, Due Date: $dueDate")
        scheduleInsuranceDueWorker(context, dueDate) // Schedule the worker based on due date
    }
}
           /* LoginFinalTheme {
                Scaffold (modifier = Modifier.fillMaxSize()){innerPaddig ->
                    MyNavigation(modifier = Modifier.padding(innerPaddig), authViewModel = authViewModel)

                }
            }




        */