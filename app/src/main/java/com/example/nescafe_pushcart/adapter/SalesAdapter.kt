package com.example.nescafe_pushcart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nescafe_pushcart.databinding.ItemListSalesRecordBinding
import com.example.nescafe_pushcart.databinding.ItemListSalesRecordBinding.*
import com.example.nescafe_pushcart.model.Sales

class SalesAdapter(val sales:ArrayList<Sales>, val onClickListener:OnClickListener):
    ListAdapter<Sales, SalesAdapter.SalesViewHolder>(DiffCallback) {

    class SalesViewHolder(var binding:ItemListSalesRecordBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(sales:Sales){

            binding.sales = sales

            binding.executePendingBindings()

        }


    }

    companion object DiffCallback: DiffUtil.ItemCallback<Sales>(){
        override fun areItemsTheSame(oldItem: Sales, newItem: Sales): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Sales, newItem: Sales): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        return SalesViewHolder(ItemListSalesRecordBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        val sales = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(sales)
        }



        holder.bind(sales)
    }

    // this was the original implementation for the general click on the card
    class OnClickListener(val clickListener:(sales:Sales) -> Unit){

        fun onClick(sales:Sales) = clickListener(sales)


    }




}