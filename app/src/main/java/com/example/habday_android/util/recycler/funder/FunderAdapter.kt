package com.example.habday_android.util.recycler.funder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.habday_android.databinding.ItemFunderListRecyclerBinding

class FunderAdapter(val context: FragmentActivity?): RecyclerView.Adapter<ViewHolder>() {

    var funderdatas = mutableListOf<FunderData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var itemBinding = ItemFunderListRecyclerBinding.inflate(layoutInflater, parent, false)
        return FunderViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as FunderViewHolder).bind(funderdatas[position])
    }

    override fun getItemCount(): Int = funderdatas.size

    class FunderViewHolder(val context: FragmentActivity?, val binding: ItemFunderListRecyclerBinding)
        : ViewHolder(binding.root){
            private val testText: TextView = binding.tvFunderName

            fun bind(item: FunderData){
                testText.text = item.name

            }
        }
}