package com.example.habday_android.src.main.add.finish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityFinishAddingFundingBinding

class FinishAddingFundingActivity : BaseActivity<ActivityFinishAddingFundingBinding>(ActivityFinishAddingFundingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToHome()
    }

    private fun navigateToHome(){
        binding.btnGoToHome.setOnClickListener {
            finish()
        }
    }
}
