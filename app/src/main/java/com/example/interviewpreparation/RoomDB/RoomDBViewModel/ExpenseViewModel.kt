package com.example.interviewpreparation.RoomDB.RoomDBViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.interviewpreparation.RoomDB.Data.ExpenseUser
import com.example.interviewpreparation.RoomDB.DataBase.ExpenseDataBase
import com.example.interviewpreparation.RoomDB.RoomDBRepository.ExpenseUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


public class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
//class ExpenseViewModel(private val repository: ExpenseUserRepository) : ViewModel() {


    var readExpenseUserData : LiveData<List<ExpenseUser>>? = null
    // get() = ExpenseUserRepository.readExpenseUserData
    private val expenseUserRepository : ExpenseUserRepository

    fun read() : LiveData<List<ExpenseUser>> {
        return readExpenseUserData!!
    }

    fun readUser() {
        readExpenseUserData = expenseUserRepository.readExpenseUserData
    }

    init {
        val ExpenseUserDAO = ExpenseDataBase.getDataBase(application).ExpenseDAO()
        expenseUserRepository = ExpenseUserRepository(ExpenseUserDAO)
    }

   /* fun readUser() {
        readExpenseUserData = ExpenseUserRepository.readUserData
    }*/

    fun addExpenseUser(expenseUser: ExpenseUser) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseUserRepository.addExpenseUser(expenseUser)
        }
    }


}