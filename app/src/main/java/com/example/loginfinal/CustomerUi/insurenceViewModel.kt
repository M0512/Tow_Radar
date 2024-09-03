package com.example.loginfinal.CustomerUi

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class insurenceViewModel(private val context: Context) : ViewModel() {
    private val _countdown = MutableLiveData<Long>()
    private val _dueDate = MutableLiveData<String>()

    val countdown: LiveData<Long> = _countdown
    val dueDate: LiveData<String> = _dueDate

    fun scheduleNotification(insuredDate: String, dueDate: String) {
        // Calculate the delay and schedule the worker
        val delay = calculateDelay(dueDate)
        scheduleInsuranceDueWorker(context, dueDate, delay)
        _countdown.value = delay / 1000 // Start the countdown
        _dueDate.value = dueDate
    }
}