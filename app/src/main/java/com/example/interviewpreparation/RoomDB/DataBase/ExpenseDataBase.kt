package com.example.interviewpreparation.RoomDB.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.interviewpreparation.RoomDB.Dao.ExpenseDAO
import com.example.interviewpreparation.RoomDB.Data.ExpenseUser
import com.example.interviewpreparation.RoomDB.Data.User

@Database(entities = [ExpenseUser::class], version = 2, exportSchema = false)
abstract class ExpenseDataBase : RoomDatabase() {

    abstract fun ExpenseDAO() : ExpenseDAO

    companion object {
        @Volatile
        private var INSTANCE : ExpenseDataBase? = null

        fun getDataBase(context: Context) : ExpenseDataBase {
            val tempInstacew = INSTANCE
            if(tempInstacew!=null) {
                return tempInstacew
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDataBase::class.java,
                    "expense_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}