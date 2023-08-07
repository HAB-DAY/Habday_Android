package com.example.habday_android.src.main.add.finish

import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityFinishAddingFundingBinding
import com.example.habday_android.src.main.add.AddFundingActivity

class FinishAddingFundingActivity : BaseActivity<ActivityFinishAddingFundingBinding>(ActivityFinishAddingFundingBinding::inflate) {
    lateinit var shareLink : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getLink()
        shareLink()
        navigateToHome()
    }

    private fun getLink(){
        shareLink = intent.getStringExtra("shareLink").toString()
        Log.d("shareLink2", shareLink)
    }
    
    private fun shareLink(){
        // 클립보드 복사
        val clipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", shareLink)

        binding.btnShareLink.setOnClickListener {
            clipboard.setPrimaryClip(clip)
            showCustomToast("클립보드에 복사되었습니다")
       }
    }

    private fun navigateToHome(){
        binding.btnGoToHome.setOnClickListener {
            finish()
        }
    }
}
