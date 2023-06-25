package com.example.habday_android.util.recycler.finish

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.habday_android.databinding.ItemFinishedFundingRecyclerBinding

class FinishAdapter(val context: FragmentActivity?): RecyclerView.Adapter<ViewHolder>() {

    var finishdatas = mutableListOf<FinishData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemFinishedFundingRecyclerBinding.inflate(layoutInflater, parent, false)
        return FinishFundingViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as FinishFundingViewHolder).bind(finishdatas[position])
    }

    override fun getItemCount(): Int = finishdatas.size

    class FinishFundingViewHolder(val context: FragmentActivity?, val binding: ItemFinishedFundingRecyclerBinding): RecyclerView.ViewHolder(binding.root){
        private val testText : TextView = binding.tvFinishFundingTitle

        fun bind(item: FinishData){
            testText.text = item.title
        }
    }
}