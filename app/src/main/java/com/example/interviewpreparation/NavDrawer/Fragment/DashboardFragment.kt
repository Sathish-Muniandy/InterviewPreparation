package com.example.interviewpreparation.NavDrawer.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.interviewpreparation.R
import kotlinx.coroutines.launch


class DashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewLifecycleOwner.lifecycleScope.launch {

        }
        lifecycleScope.launch {

        }

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


}