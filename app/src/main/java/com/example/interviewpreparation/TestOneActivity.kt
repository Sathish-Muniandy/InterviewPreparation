package com.example.interviewpreparation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.interviewpreparation.databinding.ActivityTestOneBinding

class TestOneActivity : AppCompatActivity() {

   // private lateinit var binding : ActivityTestOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  binding = DataBindingUtil.setContentView(this,R.layout.activity_test_one)
        setContentView(R.layout.activity_test_one)
    }
}