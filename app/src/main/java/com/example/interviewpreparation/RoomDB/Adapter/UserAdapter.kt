package com.example.interviewpreparation.RoomDB.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewpreparation.R
import com.example.interviewpreparation.RoomDB.Data.User
import kotlin.math.ln

class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setData (list : List<User>) {
        this.userList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).
                inflate(R.layout.room_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        val id = holder.itemView.findViewById<TextView>(R.id.user_id)
        val fname = holder.itemView.findViewById<TextView>(R.id.userf_name)
        val lname = holder.itemView.findViewById<TextView>(R.id.userl_name)
        val age = holder.itemView.findViewById<TextView>(R.id.user_age)

        id.text = currentItem.id.toString()
        fname.text = currentItem.firstName
        lname.text = currentItem.lastName
        age.text = currentItem.age.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }


}