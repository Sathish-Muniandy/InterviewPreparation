package com.example.interviewpreparation.RoomDB.RoomDBViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewpreparation.RoomDB.Data.User
import com.example.interviewpreparation.RoomDB.DataBase.UserDataBase
import com.example.interviewpreparation.RoomDB.RoomDBRepository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


public class UserViewModel(application: Application) : AndroidViewModel(application) {
//class UserViewModel(private val repository: UserRepository) : ViewModel() {


    var readUserData : LiveData<List<User>>? = null
     // get() = userRepository.readUserData
    private val userRepository : UserRepository

    fun read() : LiveData<List<User>>{
        return readUserData!!
    }

    init {
        val userDAO = UserDataBase.getDataBase(application).userDao()
        userRepository = UserRepository(userDAO)
    }

    fun readUser() {
        readUserData = userRepository.readUserData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }


}