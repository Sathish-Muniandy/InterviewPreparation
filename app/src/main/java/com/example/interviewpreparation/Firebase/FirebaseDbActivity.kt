package com.example.interviewpreparation.Firebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.interviewpreparation.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class FirebaseDbActivity : AppCompatActivity() {

    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.getReference("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_db)


       // databaseReference.setValue("Kanagaraj")
        databaseReference.child("childone").setValue("Kanagaraj")
        databaseReference.child("childtwo").setValue("Kanagaraj")
        databaseReference.child("childethree").setValue("Kanagaraj")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val hashMap = dataSnapshot.getValue() as HashMap<String,String>
                for (key in hashMap.keys) {
                    println("key: $key")
                    println("value: " + hashMap[key])
                }
                Log.i( "output:",hashMap.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.i( "Error:", error.message.toString())
            }
        })
    }
}