package com.example.nescafe_pushcart.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.example.nescafe_pushcart.R
import kotlinx.android.synthetic.main.fragment_vendor_profile.*

/**
 * A simple [Fragment] subclass.
 */
class VendorProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vendor_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thisFab.setOnClickListener {
            findNavController().navigate(R.id.action_vendorProfile_to_vendorAuthFragment)
        }

    }

}
