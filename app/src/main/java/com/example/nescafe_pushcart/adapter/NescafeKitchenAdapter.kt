package com.example.nescafe_pushcart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nescafe_pushcart.databinding.FragmentNescafeKitchenBinding
import com.example.nescafe_pushcart.databinding.ListItemVendorinkitchenBinding
import com.example.nescafe_pushcart.model.listofvendors.Content
import com.example.nescafe_pushcart.model.listofvendors.VendorList

class NescafeKitchenAdapter(val onClickListener:OnClickListener):ListAdapter<Content, NescafeKitchenAdapter.NescafeKitchenViewHolder>(DiffCallback) {


    class NescafeKitchenViewHolder(var binding: ListItemVendorinkitchenBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(content: Content){
            binding.content = content

            binding.executePendingBindings()
        }

    }

    companion object DiffCallback:DiffUtil.ItemCallback<Content>(){
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem.id == oldItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NescafeKitchenViewHolder {
        return NescafeKitchenViewHolder(ListItemVendorinkitchenBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NescafeKitchenViewHolder, position: Int) {
        val vendorList = getItem(position)

        holder.bind(vendorList)

    }

    class OnClickListener(val clickListener:(content:Content) -> Unit){

        fun onClick(content: Content) = clickListener(content)

    }

}