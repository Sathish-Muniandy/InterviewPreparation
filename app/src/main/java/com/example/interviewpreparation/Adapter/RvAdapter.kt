package com.example.interviewpreparation.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewpreparation.ApiInterface.RvPosition
import com.example.interviewpreparation.R
import com.example.interviewpreparation.databinding.AddLayoutBinding

public class RvAdapter(length: Int, rvPosition: RvPosition) : RecyclerView.Adapter<RvAdapter.MyViewHolder>()  {

    var length:Int = length
    var rvPosition = rvPosition

    /*constructor(length: Int) {
        this.length = length
    }*/


    lateinit var binding : AddLayoutBinding
    //inner class MyViewHolder(binding : AddLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view)

    fun RvLength(length:Int) {
        this.length = length
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        /*binding = AddLayoutBinding.inflate(LayoutInflater.from(parent.context),
                        parent,false)
        return MyViewHolder(binding.root)*/

        val view = LayoutInflater.from(parent.context).inflate(R.layout.add_layout,
                    parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       /* with(holder) {
            binding.nameEt.setText("sathish")
        }*/
     //   rvPosition.getPosition(position)
        val img = holder.itemView.findViewById<ImageView>(R.id.add_iv)
        img.setOnClickListener(View.OnClickListener {
            rvPosition.getPosition(position)
        })
    }

    override fun getItemCount(): Int {
        return length
    }
}