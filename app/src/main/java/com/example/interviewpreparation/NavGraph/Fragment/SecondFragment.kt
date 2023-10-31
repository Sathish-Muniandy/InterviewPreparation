package com.example.interviewpreparation.NavGraph.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.interviewpreparation.R
import com.example.interviewpreparation.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    lateinit var binding : FragmentSecondBinding
    private val args : SecondFragmentArgs by navArgs()
    var name = "Raj"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            name = args.name
          //  Toast.makeText(context, args.age.toString(), Toast.LENGTH_SHORT).show()
            binding.receiveName.setText(name)// + args.age.toString()
        }
    }

}