package com.example.interviewpreparation.RoomDB.Fragment

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.interviewpreparation.DaggerDI.DiApplicaton
import com.example.interviewpreparation.R
import com.example.interviewpreparation.RoomDB.Dao.UserDAO
import com.example.interviewpreparation.RoomDB.Data.User
import com.example.interviewpreparation.RoomDB.DataBase.UserDataBase
import com.example.interviewpreparation.RoomDB.RoomDBActivity
import com.example.interviewpreparation.RoomDB.RoomDBRepository.UserRepository
import com.example.interviewpreparation.RoomDB.RoomDBViewModel.UserViewModel
import com.example.interviewpreparation.RoomDB.RoomDBViewModel.UserViewModelFactory
import com.example.interviewpreparation.ViewModel.MainViewModelFactory
import com.example.interviewpreparation.ViewModel.ProductViewModel
import javax.inject.Inject


class AddFragment : Fragment() {

    private lateinit var fname : EditText
    private lateinit var lname : EditText
    private lateinit var age : EditText
    private lateinit var btn : Button
    private lateinit var userViewModel: UserViewModel
    var mUserViewModel: UserViewModel? = null
    var firstName = ""
    var lastName = ""
    var userAge = ""

   // private val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        val ctx = requireActivity().application
        userViewModel  = ViewModelProvider(this).get(UserViewModel::class.java)

        fname = view.findViewById<EditText>(R.id.firstname)
        lname = view.findViewById<EditText>(R.id.lastname)
        age = view.findViewById<EditText>(R.id.age)
        btn = view.findViewById<Button>(R.id.button)

        btn.setOnClickListener {

            firstName = fname.text.toString()
            lastName = lname.text.toString()
            userAge = age.text.toString()
            if(checkValidation(firstName,lastName,
                        userAge)) {
                insertData()
            }else {
                Toast.makeText(context,"Please fill all fields",Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    fun insertData() {
        val user1 = User(11,"JAN-2023",lastName,Integer.parseInt(userAge))
        userViewModel.addUser(user1)
        Toast.makeText(context,"insert successfully",Toast.LENGTH_LONG).show()
    }

    fun checkValidation(fname : String,lname : String, age : String) : Boolean {

        if(!TextUtils.isEmpty(fname) && !TextUtils.isEmpty(lname)
            && !TextUtils.isEmpty(age)) {
            return true
        }
        return false
    }

}