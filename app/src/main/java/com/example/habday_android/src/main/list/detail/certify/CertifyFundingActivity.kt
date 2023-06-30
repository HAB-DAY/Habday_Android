package com.example.habday_android.src.main.list.detail.certify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityCertifyFundingBinding

class CertifyFundingActivity : BaseActivity<ActivityCertifyFundingBinding>(ActivityCertifyFundingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToDetail()
        finishCertify()
    }

    private fun finishCertify(){
        binding.tvCertifyFinish.setOnClickListener {
            finish()
        }
    }

    private fun navigateToDetail(){
        binding.ivLeftArrow.setOnClickListener {
            finish()
        }
    }
}