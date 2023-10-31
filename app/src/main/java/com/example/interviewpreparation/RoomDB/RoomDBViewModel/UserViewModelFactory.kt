package com.example.interviewpreparation.RoomDB.RoomDBViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.interviewpreparation.RoomDB.RoomDBRepository.UserRepository
import javax.inject.Inject

/*
class UserViewModelFactory constructor(private val repository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }

}*/
class UserViewModelFactory constructor(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(application) as T
    }

}