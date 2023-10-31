package com.example.interviewpreparation.NavGraph.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.interviewpreparation.R
import com.example.interviewpreparation.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    lateinit var binding : FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            binding.Send.setOnClickListener {
                val name = nameet.text.toString()
                val userAge = ageet.text.toString().toInt()
                val direction = FirstFragmentDirections.actionFirstFragmentToSecondFragment(name,userAge)
                findNavController().navigate(direction)
            }
        }
    }

}