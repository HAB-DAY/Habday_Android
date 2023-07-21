package com.example.habday_android.src.main.list.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.example.habday_android.R
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityDetailFundingBinding
import com.example.habday_android.src.main.list.detail.certify.CertifyFundingActivity
import com.example.habday_android.src.main.list.detail.delete.DeleteFundingDialog
import com.example.habday_android.src.main.list.detail.model.DetailFundingResponse
import com.example.habday_android.src.main.list.detail.modify.ModifyFundingActivity
import com.example.habday_android.util.recycler.funder.FunderAdapter
import com.example.habday_android.util.recycler.funder.FunderData

class DetailFundingActivity : BaseActivity<ActivityDetailFundingBinding>(ActivityDetailFundingBinding::inflate),
    DetailFundingView{

    lateinit var funderAdapter : FunderAdapter
    val funderdatas = mutableListOf<FunderData>()

    private var itemId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToMain()
        getItemId()

        showLoadingDialog(this)
        DetailFundingService(this).tryGetDetailFunding(itemId!!);


        modifyFunding()
        navigateToCertifyFunding()
    }

    private fun getItemId(){
        itemId = intent.getIntExtra("itemId", 0)
        Log.d("itemId", itemId.toString())
    }


    private fun settingDetails(response: DetailFundingResponse){
        binding.tvDetailFundingName.text = response.data.fundingName
        Glide.with(this)
            .load(response.data.fundingItemImg)
            .centerCrop()
            .into(binding.ivDetailFundingImg)
        binding.tvDetailFundingInformation.text = response.data.fundDetail
        binding.progressBarDetailFunding.progress = response.data.percentage
        binding.tvDetailFundingPresentCost.text = response.data.itemPrice.toInt().toString()
        binding.tvDetailFundingNowAmount.text = response.data.totalPrice.toInt().toString()
        binding.tvDetailFundingGoalAmount.text = response.data.goalPrice.toInt().toString()
        binding.tvDetailFundingTerm.text = response.data.startDate + " ~ " + response.data.finishDate

        if(response.data.status.equals("SUCCESS")){
            binding.tvSuccess.isGone = false
        }else if(response.data.status.equals("FAIL")){
            binding.tvFailure.isGone = false
        }else{
            // progress
            binding.tvProgressing.isGone = false
        }
    }

    private fun initRV(response: DetailFundingResponse){
        funderdatas.clear()

        funderAdapter = FunderAdapter(this)
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

    private fun modifyFunding(){
        binding.ivDots.setOnClickListener {
            var popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater?.inflate(R.menu.popup_menu_funding_detail, popupMenu.menu)
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.popup_modify -> {
                        startActivity(Intent(this, ModifyFundingActivity::class.java))
                        return@setOnMenuItemClickListener true
                    }
                    R.id.popup_delete -> {
                        val deleteDialog = DeleteFundingDialog(this)
                        deleteDialog.itemId = itemId
                        deleteDialog.show()
                        return@setOnMenuItemClickListener true
                    }
                    else -> {
                        return@setOnMenuItemClickListener false
                    }
                }
            }
        }
    }

    private fun navigateToCertifyFunding(){
        // 인증 가능한 기간: 1) 펀딩 기간이 끝난 이후  2) 목표 금액 달성 이후
        // 그때에 맞춰서 인증하기 버튼 활성화

        binding.tvFinish.setOnClickListener {
            startActivity(Intent(this, CertifyFundingActivity::class.java))
        }
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