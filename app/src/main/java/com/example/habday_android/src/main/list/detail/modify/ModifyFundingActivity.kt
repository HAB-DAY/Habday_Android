package com.example.habday_android.src.main.list.detail.modify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityModifyFundingBinding

class ModifyFundingActivity : BaseActivity<ActivityModifyFundingBinding>(ActivityModifyFundingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToFundingDetail()
        modifyFunding()
    }

    private fun navigateToFundingDetail(){
        binding.ivLeftArrow.setOnClickListener {
            finish()
        }
    }

    private fun modifyFunding(){
        binding.tvAddFundingFinish.setOnClickListener {
            finish()
        }
    }
}