package com.example.habday_android.util.recycler.progressing

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.habday_android.databinding.ItemProgressingFundingRecyclerBinding
import com.example.habday_android.src.main.list.detail.DetailFundingActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class ProgressingAdapter(val context: FragmentActivity?): RecyclerView.Adapter<ViewHolder>() {

    var progressingdatas = mutableListOf<ProgressingData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemProgressingFundingRecyclerBinding.inflate(layoutInflater, parent, false)
        return ProgressingFundingViewHolder(context, itemBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ProgressingFundingViewHolder).bind(progressingdatas[position])
    }

    override fun getItemCount(): Int = progressingdatas.size

    class ProgressingFundingViewHolder(val context: FragmentActivity?, val binidng: ItemProgressingFundingRecyclerBinding)
        : RecyclerView.ViewHolder(binidng.root){

            private val fundingItemImg: ImageView = binidng.ivProgressingFunding
            private val fundingName: TextView = binidng.tvProgressingFundingTitle
            private val fundingDDay: TextView = binidng.tvDDay
            private val totalPrice: TextView = binidng.tvProgressingFunderName
            private val fundingTerm: TextView = binidng.tvProgressingFundingAmount


            @RequiresApi(Build.VERSION_CODES.O)
            fun bind(item: ProgressingData){


                Glide.with(context!!)
                    .load(item.fundingItemImg)
                    .centerCrop()
                    .into(fundingItemImg)

                if(item.fundingName.length > 11){
                    fundingName.text = item.fundingName.substring(0,10) + "..."
                }else{
                    fundingName.text = item.fundingName
                }

                val onlyDate: LocalDate = LocalDate.now()
                Log.d("onlyDate", onlyDate.toString())
                fundingDDay.text = item.finishDate  // d day

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