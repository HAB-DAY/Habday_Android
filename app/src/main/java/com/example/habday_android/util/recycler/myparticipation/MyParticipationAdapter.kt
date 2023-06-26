package com.example.habday_android.util.recycler.myparticipation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.habday_android.databinding.ItemMyParticipationFundingRecyclerBinding

class MyParticipationAdapter(val context: FragmentActivity?): RecyclerView.Adapter<ViewHolder>() {

    var myParticipationdatas = mutableListOf<MyParticipationData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMyParticipationFundingRecyclerBinding.inflate(layoutInflater, parent, false)
        return MyParticipationViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as MyParticipationViewHolder).bind(myParticipationdatas[position])
    }

    override fun getItemCount(): Int = myParticipationdatas.size

    class MyParticipationViewHolder(val context: FragmentActivity?, val binding: ItemMyParticipationFundingRecyclerBinding)
        : RecyclerView.ViewHolder(binding.root){
            private val testText: TextView = binding.tvMyParticipationFundingTitle

             fun bind(item: MyParticipationData){
                 testText.text = item.title
             }
        }
}