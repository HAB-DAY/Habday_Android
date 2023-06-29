package com.example.habday_android.util.recycler.progressing

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.habday_android.databinding.ItemProgressingFundingRecyclerBinding
import com.example.habday_android.src.main.list.detail.DetailFundingActivity

class ProgressingAdapter(val context: FragmentActivity?): RecyclerView.Adapter<ViewHolder>() {

    var progressingdatas = mutableListOf<ProgressingData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemProgressingFundingRecyclerBinding.inflate(layoutInflater, parent, false)
        return ProgressingFundingViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ProgressingFundingViewHolder).bind(progressingdatas[position])
    }

    override fun getItemCount(): Int = progressingdatas.size

    class ProgressingFundingViewHolder(val context: FragmentActivity?, val binidng: ItemProgressingFundingRecyclerBinding)
        : RecyclerView.ViewHolder(binidng.root){

            private val testText: TextView = binidng.tvProgressingFundingTitle

            fun bind(item: ProgressingData){
                testText.text = item.title

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFundingActivity::class.java)
                    intent.run { itemView.context.startActivity(intent) }
                }
            }
        }
}