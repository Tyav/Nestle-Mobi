package com.example.nescafe_pushcart.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.nescafe_pushcart.R
import com.example.nescafe_pushcart.adapter.SalesAdapter
import com.example.nescafe_pushcart.databinding.FragmentVendorProfileBinding
import com.example.nescafe_pushcart.model.Sales
import kotlinx.android.synthetic.main.fragment_vendor_profile.*

/**
 * A simple [Fragment] subclass.
 */
class VendorProfile : Fragment() {

    private lateinit var binding: FragmentVendorProfileBinding
    private lateinit var adapter: SalesAdapter

    val args:VendorProfileArgs by navArgs()
    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_vendor_profile, container, false)
        binding = FragmentVendorProfileBinding.inflate(inflater, container, false)

        // list of sales
        var salesRecord = arrayListOf<Sales>()
        val sales1 = Sales(1,"01", "Apr", "No cups sold")
        val sales2 = Sales(2, "02", "Apr", "59 cups sold")
        val sales3 = Sales(3, "03", "Apr", "83 cups sold")

        // populate the list
        salesRecord.add(sales1)
        salesRecord.add(sales2)
        salesRecord.add(sales3)

        val recyclerView:RecyclerView = binding.vendorProfileRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = SalesAdapter(salesRecord, SalesAdapter.OnClickListener{

        })
        recyclerView.adapter = adapter
        adapter.submitList(salesRecord)

        binding.content = args.Content


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thisFab.setOnClickListener {
            findNavController().navigate(R.id.action_vendorProfile_to_vendorAuthFragment)
        }

    }

}
