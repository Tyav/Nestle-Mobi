package com.example.nescafe_pushcart.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nescafe_pushcart.R
import com.example.nescafe_pushcart.databinding.FragmentNescafeKitchenBinding

/**
 * A simple [Fragment] subclass.
 */
class NescafeKitchen : Fragment() {

    private lateinit var binding: FragmentNescafeKitchenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_nescafe_kitchen, container, false)

        binding = FragmentNescafeKitchenBinding.inflate(inflater, container, false)

        return binding.root

    }

}
