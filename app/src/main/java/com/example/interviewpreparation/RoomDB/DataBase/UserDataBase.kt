package com.example.interviewpreparation.RoomDB.DataBase

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.interviewpreparation.RoomDB.Dao.UserDAO
import com.example.interviewpreparation.RoomDB.Data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao() : UserDAO

    companion object {
        @Volatile
        private var INSTANCE : UserDataBase? = null

        fun getDataBase(context: Context) : UserDataBase {
            val tempInstacew = INSTANCE
            if(tempInstacew!=null) {
                return tempInstacew
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}