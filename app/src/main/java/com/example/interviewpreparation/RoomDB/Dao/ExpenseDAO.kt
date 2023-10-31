package com.example.interviewpreparation.RoomDB.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.interviewpreparation.RoomDB.Data.ExpenseUser

@Dao
interface ExpenseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExpenseUser(expenseUser : ExpenseUser)

    @Update
    suspend fun updateExpenseUser(expenseUser: ExpenseUser)

    @Delete
    suspend fun deleteExpenseUser(expenseUser: ExpenseUser)

    @Query("SELECT * FROM expense_table ORDER BY id ASC")
    fun readExpenseUserData() : LiveData<List<ExpenseUser>>

}