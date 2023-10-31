package com.example.interviewpreparation

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.getSystemService
import com.example.interviewpreparation.Servie.AppForegroundService
import com.example.interviewpreparation.Servie.AppForegroundService.MediaPlayerInstance.Companion.mediaPlayer
import com.example.interviewpreparation.databinding.ActivityServiceNotificationBinding

class ServiceNotificationActivity : AppCompatActivity() {

    lateinit var binding: ActivityServiceNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.click.setOnClickListener {
            startStopService()
        }


    }

    private fun startStopService() {
        if(isMyServiceRunning(AppForegroundService::class.java)) {
            if(mediaPlayer!=null && mediaPlayer!!.isPlaying) {
                mediaPlayer!!.stop()
            }
            Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show()
            stopService(Intent(this,AppForegroundService::class.java))
        }else {
            Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show()
            startService(Intent(this,AppForegroundService::class.java))
        }
    }

    private fun isMyServiceRunning(mClass : Class<AppForegroundService>) : Boolean {

        val manager : ActivityManager = getSystemService(
                Context.ACTIVITY_SERVICE
        ) as ActivityManager

        for(service : ActivityManager.RunningServiceInfo in
            manager.getRunningServices(Integer.MAX_VALUE)) {

            if(mClass.name.equals(service.service.className)) {
                return true
            }

        }
        return false
    }


}