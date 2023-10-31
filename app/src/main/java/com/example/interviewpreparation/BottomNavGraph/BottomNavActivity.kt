package com.example.interviewpreparation.BottomNavGraph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.interviewpreparation.NavDrawer.Fragment.DashboardFragment
import com.example.interviewpreparation.NavDrawer.Fragment.HomeFragment
import com.example.interviewpreparation.NavDrawer.Fragment.MessageFragment
import com.example.interviewpreparation.NavDrawer.Fragment.SettingsFragment
import com.example.interviewpreparation.R
import com.example.interviewpreparation.databinding.ActivityBottomNavBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavActivity : AppCompatActivity() {

    //lateinit var bottomNav : BottomNavigationView
    lateinit var binding: ActivityBottomNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> changeFragment(HomeFragment(),"Home")
                R.id.dashboard -> changeFragment(DashboardFragment(),"Dashboard")
                R.id.message -> changeFragment(MessageFragment(),"Message")
                R.id.settings -> changeFragment(SettingsFragment(),"Settings")
            }
            true
        }

    }

    override fun onResume() {
        super.onResume()
        changeFragment(HomeFragment(),"Home")
    }

    fun changeFragment(fragment: Fragment, title : String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.bottomnav_frame_layout,fragment)
        fragmentTransaction.commit()
        setTitle(title)
    }

}