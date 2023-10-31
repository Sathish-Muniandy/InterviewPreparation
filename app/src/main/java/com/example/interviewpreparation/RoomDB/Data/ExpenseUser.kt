package com.example.interviewpreparation.RoomDB.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class ExpenseUser(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val categoryId : Int,
    val amount : Int,
    val date : String,
    val monthYear : String
)