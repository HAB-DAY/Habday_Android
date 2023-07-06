package com.example.habday_android.src.main.list.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityDetailFundingBinding
import com.example.habday_android.src.main.list.detail.certify.CertifyFundingActivity
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

    private fun navigateToCertifyFunding(){
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