package com.example.nescafe_pushcart.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.nescafe_pushcart.R
import com.example.nescafe_pushcart.adapter.NescafeKitchenAdapter
import com.example.nescafe_pushcart.databinding.FragmentNescafeKitchenBinding
import com.example.nescafe_pushcart.utils.Result
import com.example.nescafe_pushcart.viewModel.NescafeKitchenViewModel

/**
 * A simple [Fragment] subclass.
 */
class NescafeKitchen : Fragment() {

    val TAG = "NESCAFEKITCHEN"

    lateinit var viewModel: NescafeKitchenViewModel

    private lateinit var binding: FragmentNescafeKitchenBinding
    private lateinit var adapter:NescafeKitchenAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_nescafe_kitchen, container, false)

        viewModel = ViewModelProvider(this).get(NescafeKitchenViewModel::class.java)


        binding = FragmentNescafeKitchenBinding.inflate(inflater, container, false)

        /**
         * connect the recyclerview to the adapter
         */

        val recyclerView:RecyclerView = binding.vRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = NescafeKitchenAdapter(
            NescafeKitchenAdapter.OnClickListener {}
        )

        recyclerView.adapter = adapter

        viewModel.myVendorList.observe(viewLifecycleOwner, Observer {

            when(it){

                is Result.Success -> {
                    it.data.data?.content.let {
                        adapter.submitList(it)
                        Log.d(TAG, "What is inside here:$it")
                    }
                }

                is Result.Error -> {
                    Toast.makeText(context, "${it.exception.message}", Toast.LENGTH_LONG).show()
                }

            }

        })


        return binding.root

    }

}
