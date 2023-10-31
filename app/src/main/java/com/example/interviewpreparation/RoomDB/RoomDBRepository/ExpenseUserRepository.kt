package com.example.interviewpreparation.RoomDB.RoomDBRepository

import androidx.lifecycle.LiveData
import com.example.interviewpreparation.RoomDB.Dao.ExpenseDAO
import com.example.interviewpreparation.RoomDB.Data.ExpenseUser


class ExpenseUserRepository(private val ExpenseDAO: ExpenseDAO) {

    val readExpenseUserData : LiveData<List<ExpenseUser>>
        get() = ExpenseDAO.readExpenseUserData()


    /*suspend fun readExpenseUser() {
        readExpenseUserData = ExpenseDAO.readExpenseUserData()
    }*/

    suspend fun addExpenseUser(expenseUser: ExpenseUser) {
        ExpenseDAO.addExpenseUser(expenseUser)
    }

}