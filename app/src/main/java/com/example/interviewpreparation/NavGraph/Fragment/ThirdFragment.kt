package com.example.interviewpreparation.NavGraph.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.interviewpreparation.R
import com.example.interviewpreparation.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    lateinit var binding : FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdBinding.inflate(inflater,container,false)
        return binding.root
    }

}