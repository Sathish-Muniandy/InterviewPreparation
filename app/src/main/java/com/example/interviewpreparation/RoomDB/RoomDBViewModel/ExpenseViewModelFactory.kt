package com.example.interviewpreparation.RoomDB.RoomDBViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ExpenseViewModelFactory constructor(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExpenseViewModel(application) as T
    }

}