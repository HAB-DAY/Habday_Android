package com.example.habday_android.src.main.list.myparticipation.detail

import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityMyParticipationDetailFundingBinding
import com.example.habday_android.src.main.list.detail.DetailFundingService
import com.example.habday_android.src.main.list.detail.DetailFundingView
import com.example.habday_android.src.main.list.detail.model.DetailFundingResponse
import com.example.habday_android.src.main.list.myparticipation.model.MyParticipationFundingResponse
import com.example.habday_android.util.recycler.funder.FunderAdapter
import com.example.habday_android.util.recycler.funder.FunderData
import com.example.habday_android.util.recycler.funder.myparticipation.MyParticipationFunderAdapter

class MyParticipationDetailFundingActivity : BaseActivity<ActivityMyParticipationDetailFundingBinding>(ActivityMyParticipationDetailFundingBinding::inflate), DetailFundingView {

    lateinit var funderAdapter : MyParticipationFunderAdapter
    val funderdatas = mutableListOf<FunderData>()
    var shareLink: String ?= null

    private var itemId : Int? = null
    private var fundingAmount : Int ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()

        navigateToMain()
        getItemIdAndFundingAmount()

        showLoadingDialog(this)
        DetailFundingService(this).tryGetDetailFunding(itemId!!)

        shareLink()
    }

    private fun shareLink(){
        // 클립보드 복사
        val clipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", shareLink)

        binding.ivShare.setOnClickListener {
            clipboard.setPrimaryClip(clip)
            showCustomToast("클립보드에 복사되었습니다")
        }
    }

    private fun initRV(response: DetailFundingResponse){
        funderdatas.clear()

        funderAdapter = MyParticipationFunderAdapter(this)
        binding.recyclerDetailFunding.adapter = funderAdapter

        // 댓글
        for(i in response.data.fundingParticipantList.indices){
            funderdatas.apply { add(FunderData(
                name = response.data.fundingParticipantList[i].name,
                fundingDate = response.data.fundingParticipantList[i].fundingDate,
                amount = response.data.fundingParticipantList[i].amount,
                message = response.data.fundingParticipantList[i].message
            )) }
        }


        funderAdapter.funderdatas = funderdatas
        funderAdapter.notifyDataSetChanged()

        settingDetails(response)
    }

    private fun getItemIdAndFundingAmount(){
        itemId = intent.getIntExtra("itemId", 0)
        fundingAmount = intent.getDoubleExtra("fundingAmount", 0.0).toInt()
    }

    private fun settingDetails(response: DetailFundingResponse){
        binding.tvDetailFundingMy.text = "내가 선물한 금액: " + fundingAmount + "원"

        binding.tvDetailFundingName.text = response.data.fundingName
        Glide.with(this)
            .load(response.data.fundingItemImg)
            .centerCrop()
            .into(binding.ivDetailFundingImg)

        binding.tvDetailFundingInformation.text = response.data.fundDetail
        binding.progressBarDetailFunding.progress = response.data.percentage
        binding.tvDetailFundingPresentCost.text = response.data.itemPrice.toInt().toString() + "원"

        binding.tvDetailFundingNowAmount.text = response.data.totalPrice.toInt().toString() + "원"
        binding.tvDetailFundingPbNow.text = "현재 금액 " + response.data.totalPrice.toInt().toString() + "원"

        binding.tvDetailFundingGoalAmount.text = response.data.goalPrice.toInt().toString() + "원"
        binding.tvDetailFundingPbGoal.text = "목표 금액 " + response.data.goalPrice.toInt().toString() + "원"

        binding.tvDetailFundingTerm.text = response.data.startDate + " ~ " + response.data.finishDate
        shareLink = response.data.showDetailUrl

        if(response.data.status.equals("SUCCESS")){
            binding.tvSuccess.isGone = false
        }else if(response.data.status.equals("FAIL")){
            binding.tvFailure.isGone = false
        }else{
            // progress
            binding.tvProgressing.isGone = false
        }

        binding.tvDetailFundingFunderNum.text = response.data.fundingParticipantList.size.toString()
    }

    private fun navigateToMain(){
        binding.ivLeftArrow.setOnClickListener {
            finish()
        }
    }

    override fun onGetDetailFundingSuccess(response: DetailFundingResponse) {
        dismissLoadingDialog()
        initRV(response)
    }

    override fun onGetDetailFundingFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("로딩에 실패했습니다")
    }
}