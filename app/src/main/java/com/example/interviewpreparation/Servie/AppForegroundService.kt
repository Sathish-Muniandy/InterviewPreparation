package com.example.interviewpreparation.Servie

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.example.interviewpreparation.MainActivity
import com.example.interviewpreparation.R
import com.example.interviewpreparation.Servie.AppForegroundService.MediaPlayerInstance.Companion.mediaPlayer
import com.example.interviewpreparation.Utils.Constants.CHANNEL_ID
import com.example.interviewpreparation.Utils.Constants.MUSIC_NOTIFICATION_ID
import java.security.Provider

class AppForegroundService : Service() {

    class MediaPlayerInstance {
        companion object {
           var mediaPlayer : MediaPlayer? = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        initMusic()
        createNotificationChannel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        showNotification()
        if(mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
        }else {
            mediaPlayer!!.start()
        }

        return START_STICKY

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification() {
        val notificationIntent = Intent(this,MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            this,0,notificationIntent,0
        )

        val notification = Notification
            .Builder(this, CHANNEL_ID)
            .setContentText("Sathish Android Developer")
            .setSmallIcon(R.drawable.user)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(MUSIC_NOTIFICATION_ID,notification)
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val serviceChannel = NotificationChannel(
                CHANNEL_ID,"My Servce Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(
                NotificationManager::class.java
            )

            manager.createNotificationChannel(serviceChannel)

        }
    }

    private fun initMusic() {
        mediaPlayer = MediaPlayer.create(this,R.raw.music)
      //  mediaPlayer!!.isLooping = true
        mediaPlayer!!.setVolume(60f,60f)
    }

}