package com.seda.e_commerceappp.PayFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.seda.e_commerceappp.Dashboard.dashboardActivity
import com.seda.e_commerceappp.R
import com.seda.e_commerceappp.databinding.ActivitySuccessBinding
import com.seda.e_commerceappp.databinding.FragmentGoToPayBinding
import com.seda.e_commerceappp.databinding.FragmentSuccessBinding


class SuccessFragment : Fragment() {
    private lateinit var binding: FragmentSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shop.setOnClickListener {
        Navigation.findNavController(it).navigate(R.id.action_successFragment_to_homeFragment)

        }

    }


}