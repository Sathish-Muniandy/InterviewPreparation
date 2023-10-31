package com.example.interviewpreparation.DaggerDI

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class DiApplicaton : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.i("token failed", task.exception.toString())
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            /*val msg = getString(R.string.msg_token_fmt, token)
            Log.d(TAG, msg)*/
          //  Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })

        FirebaseMessaging.getInstance().subscribeToTopic("all")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("subscribe:","success" )
                }else{
                    Log.i("subscribe:","failure" )
                }
            }

        applicationComponent = DaggerApplicationComponent.builder().build()
    }

}