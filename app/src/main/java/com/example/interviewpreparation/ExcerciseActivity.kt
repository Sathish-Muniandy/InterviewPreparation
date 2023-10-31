package com.example.interviewpreparation

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.example.interviewpreparation.databinding.ActivityDriverBinding

class ExcerciseActivity : AppCompatActivity() {

    lateinit var binding : ActivityDriverBinding
    lateinit var media : MediaPlayer
    lateinit var video : VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriverBinding.inflate(layoutInflater)
        setContentView(binding.root)
     //   setContentView(R.layout.activity_driver)

        media = MediaPlayer.create(this,R.raw.music)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)

        val path = "android.resource://" + packageName + "/" + "R.raw.shani"
        val path1 = "R.raw.vsong.mp4"
        val uri = Uri.parse(path)
      //  binding.videoView.setMediaController(mediaController)
      //  binding.videoView.setVideoURI(uri)
        binding.videoView.setVideoPath(path)
       // binding.videoView.requestFocus()
        binding.videoView.start()


        binding.apply {

            button1.setOnClickListener(View.OnClickListener {
                Toast.makeText(this@ExcerciseActivity, "Click", Toast.LENGTH_SHORT).show()
                media.start()
            })
            button2.setOnClickListener(View.OnClickListener {
                media.pause()
            })
        }


    }
}