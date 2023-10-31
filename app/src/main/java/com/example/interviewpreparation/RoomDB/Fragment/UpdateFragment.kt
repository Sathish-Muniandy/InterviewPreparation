package com.example.interviewpreparation.RoomDB.Fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.interviewpreparation.R
import com.example.interviewpreparation.RoomDB.Data.User
import com.example.interviewpreparation.RoomDB.RoomDBViewModel.UserViewModel

class UpdateFragment : Fragment() {

    private lateinit var fname : EditText
    private lateinit var lname : EditText
    private lateinit var age : EditText
    private lateinit var btn : Button
    private lateinit var userViewModel: UserViewModel
    var mUserViewModel: UserViewModel? = null
    var firstName = ""
    var lastName = ""
    var userAge = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel  = ViewModelProvider(this).get(UserViewModel::class.java)

        fname = view.findViewById<EditText>(R.id.nameupdate)
        lname = view.findViewById<EditText>(R.id.lnameupdate)
        age = view.findViewById<EditText>(R.id.ageupdate)
        btn = view.findViewById<Button>(R.id.updatebtn)

        btn.setOnClickListener {

            firstName = fname.text.toString()
            lastName = lname.text.toString()
            userAge = age.text.toString()
            if(checkValidation(firstName,lastName,
                    userAge)) {
                updateData()
            }else {
                Toast.makeText(context,"Please fill all fields", Toast.LENGTH_LONG).show()
            }
        }


        return view
    }

    fun updateData() {
        val user = User(1,firstName,lastName,Integer.parseInt(userAge))
        userViewModel.updateUser(user)
        Toast.makeText(context,"Update successfully",Toast.LENGTH_LONG).show()
    }

    fun checkValidation(fname : String,lname : String, age : String) : Boolean {

        if(!TextUtils.isEmpty(fname) && !TextUtils.isEmpty(lname)
            && !TextUtils.isEmpty(age)) {
            return true
        }
        return false
    }
}