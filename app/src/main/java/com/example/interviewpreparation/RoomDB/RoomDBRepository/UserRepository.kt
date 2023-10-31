package com.example.interviewpreparation.RoomDB.RoomDBRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.interviewpreparation.RoomDB.Dao.UserDAO
import com.example.interviewpreparation.RoomDB.Data.User

class UserRepository(private val userDAO: UserDAO) {

    val readUserData : LiveData<List<User>>
       get() = userDAO.readUserData()


    /*suspend fun readUser() {
        readUserData = userDAO.readUserData()
    }*/

    suspend fun addUser(user: User) {
        userDAO.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDAO.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDAO.deleteUser(user)
    }

}