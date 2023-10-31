package com.example.interviewpreparation.RoomDB.Fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewpreparation.R
import com.example.interviewpreparation.RoomDB.Adapter.UserAdapter
import com.example.interviewpreparation.RoomDB.Data.User
import com.example.interviewpreparation.RoomDB.RoomDBViewModel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val floatingActionButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val floatingActionButton2 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton2)

        val userRv = view.findViewById<RecyclerView>(R.id.room_rv)
        val adapter = UserAdapter()
        userRv.adapter = adapter
        userRv.layoutManager = LinearLayoutManager(requireContext())

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.readUser()
        userViewModel.readUserData!!.observe(viewLifecycleOwner, Observer { user ->
            Log.i("Listuser:",user.toString())
            adapter.setData(user)

            //new code


        })

       /* userViewModel.readUsers()
        userViewModel.readUserData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })*/


        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        setHasOptionsMenu(true)

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_menu) {
            userViewModel.deleteUser(User(5,"ghh","ghhh",78))
            Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}