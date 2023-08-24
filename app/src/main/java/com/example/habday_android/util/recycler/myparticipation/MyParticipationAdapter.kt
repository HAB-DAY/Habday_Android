package com.example.habday_android.util.recycler.myparticipation

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.habday_android.databinding.ItemMyParticipationFundingRecyclerBinding
import com.example.habday_android.src.main.list.detail.DetailFundingActivity
import com.example.habday_android.src.main.list.myparticipation.detail.MyParticipationDetailFundingActivity

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
            private val fundingItemImg: ImageView = binding.ivMyParticipationFunding
            private val fundingName: TextView = binding.tvMyParticipationFundingTitle
            private val creatorName: TextView = binding.tvMyParticipationFundingCost
            private val fundingAmount: TextView = binding.tvMyParticipationFundingTerm

             fun bind(item: MyParticipationData){
                 Glide.with(context!!)
                     .load(item.fundingItemImg)
                     .centerCrop()
                     .into(fundingItemImg)

                 fundingName.text = item.fundingName
                 creatorName.text = item.creatorName
                 fundingAmount.text = "선물한 금액: " + item.fundingAmount.toInt().toString() + "원"

                 itemView.setOnClickListener {
                     val intent = Intent(itemView.context, MyParticipationDetailFundingActivity::class.java)
                     intent.putExtra("itemId", item.fundingItemId)
                     intent.putExtra("fundingAmount", item.fundingAmount)
                     intent.run { itemView.context.startActivity(intent) }
                 }
             }
        }
}