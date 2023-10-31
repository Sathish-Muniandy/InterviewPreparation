package com.example.interviewpreparation.NavDrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.interviewpreparation.NavDrawer.Fragment.DashboardFragment
import com.example.interviewpreparation.NavDrawer.Fragment.HomeFragment
import com.example.interviewpreparation.NavDrawer.Fragment.MessageFragment
import com.example.interviewpreparation.NavDrawer.Fragment.SettingsFragment
import com.example.interviewpreparation.R
import com.google.android.material.navigation.NavigationView

class DrawerActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var navView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        changeFragment(HomeFragment(),"Home")
        navView.setNavigationItemSelectedListener {
            when(it.itemId) {

                R.id.home -> changeFragment(HomeFragment(),"Home")
                R.id.dashboard -> changeFragment(DashboardFragment(),"Dashboard")
                R.id.message -> changeFragment(MessageFragment(),"Message")
                R.id.settings -> changeFragment(SettingsFragment(),"Settings")

            }
            true
        }
    }

    fun changeFragment(fragment: Fragment,title : String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
        setTitle(title)
        drawerLayout.close()
    }


}