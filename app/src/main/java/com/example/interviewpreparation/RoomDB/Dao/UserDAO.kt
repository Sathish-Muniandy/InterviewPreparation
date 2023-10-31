package com.example.interviewpreparation.RoomDB.Dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.interviewpreparation.RoomDB.Data.User
import retrofit2.Response

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addUser(user : User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readUserData() : LiveData<List<User>>

}