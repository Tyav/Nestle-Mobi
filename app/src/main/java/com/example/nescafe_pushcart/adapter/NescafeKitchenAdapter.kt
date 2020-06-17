package com.example.nescafe_pushcart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nescafe_pushcart.databinding.FragmentNescafeKitchenBinding
import com.example.nescafe_pushcart.databinding.ListItemVendorinkitchenBinding
import com.example.nescafe_pushcart.model.listofvendors.VendorList

class NescafeKitchenAdapter(val onClickListener:OnClickListener):ListAdapter<VendorList, NescafeKitchenAdapter.NescafeKitchenViewHolder>(DiffCallback) {


    class NescafeKitchenViewHolder(var binding: ListItemVendorinkitchenBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(vendorList: VendorList){
            binding.vendorList = vendorList

            binding.executePendingBindings()
        }

    }

    companion object DiffCallback:DiffUtil.ItemCallback<VendorList>(){
        override fun areItemsTheSame(oldItem: VendorList, newItem: VendorList): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: VendorList, newItem: VendorList): Boolean {
            return oldItem.data == oldItem.data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NescafeKitchenViewHolder {
        return NescafeKitchenViewHolder(ListItemVendorinkitchenBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NescafeKitchenViewHolder, position: Int) {
        val vendorList = getItem(position)

        holder.bind(vendorList)

    }

    class OnClickListener(val clickListener:(vendorList:VendorList) -> Unit){

        fun onClick(vendorList:VendorList) = clickListener(vendorList)

    }

}