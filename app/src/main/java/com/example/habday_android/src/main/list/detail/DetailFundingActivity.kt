package com.example.habday_android.src.main.list.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import com.example.habday_android.R
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityDetailFundingBinding
import com.example.habday_android.src.main.list.detail.certify.CertifyFundingActivity
import com.example.habday_android.src.main.list.detail.modify.ModifyFundingActivity
import com.example.habday_android.util.recycler.funder.FunderAdapter
import com.example.habday_android.util.recycler.funder.FunderData

class DetailFundingActivity : BaseActivity<ActivityDetailFundingBinding>(ActivityDetailFundingBinding::inflate) {

    lateinit var funderAdapter : FunderAdapter
    val funderdatas = mutableListOf<FunderData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToMain()
        tempSettingProgressBar()
        initRV()
        modifyFunding()
        navigateToCertifyFunding()
    }


    private fun tempSettingProgressBar(){
        binding.progressBarDetailFunding.progress = 30 // 30%
    }

    private fun initRV(){
        funderdatas.clear()

        funderAdapter = FunderAdapter(this)
        binding.recyclerDetailFunding.adapter = funderAdapter

        for(i in 1 until 4){
            Log.d("funder", i.toString())
            funderdatas.apply { add(FunderData(name = "test 1")) }
        }

        funderAdapter.funderdatas = funderdatas
        funderAdapter.notifyDataSetChanged()
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
                    }else -> {
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
}