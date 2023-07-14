package com.example.habday_android.util.recycler.finish

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isGone
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.habday_android.databinding.ItemFinishedFundingRecyclerBinding
import com.example.habday_android.src.main.list.detail.DetailFundingActivity

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

    class FinishFundingViewHolder(val context: FragmentActivity?, val binding: ItemFinishedFundingRecyclerBinding)
        : RecyclerView.ViewHolder(binding.root){
        private val fundingItemImg: ImageView = binding.ivFinishFunding
        private val fundingName: TextView = binding.tvFinishFundingTitle
        private val statusSuccess: TextView = binding.tvSuccess
        private val statusFailure: TextView = binding.tvFailure
        private val totalPrice: TextView = binding.tvFinishFundingCost
        private val fundingTerm: TextView = binding.tvFinishFundingTerm

        fun bind(item: FinishData){

            Glide.with(context!!)
                .load(item.fundingItemImg)
                .centerCrop()
                .into(fundingItemImg)

            if(item.fundingName.length > 11){
                fundingName.text = item.fundingName.substring(0,10) + "..."
            }else{
                fundingName.text = item.fundingName
            }


            if(item.status.equals("SUCCESS")){
                statusSuccess.isGone = false
                statusFailure.isGone = true
            }else{
                statusSuccess.isGone = true
                statusFailure.isGone = false
            }

            totalPrice.text = item.totalPrice.toString()
            fundingTerm.text = item.startDate + " ~ " + item.finishDate

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailFundingActivity::class.java)
                intent.putExtra("itemId", item.id)
                intent.run { itemView.context.startActivity(intent) }
            }
        }
    }
}