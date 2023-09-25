package com.example.habday_android.src.main.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.habday_android.R
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivitySettingsBinding
import com.example.habday_android.src.login.addinfo.AddInformationActivity
import com.example.habday_android.src.main.settings.logout.LogoutDialog

class SettingsActivity : BaseActivity<ActivitySettingsBinding>(ActivitySettingsBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToBack()
        changeInfo()
        logOut()
    }

    private fun navigateToBack(){
        binding.ivLeftArrow.setOnClickListener { finish() }
    }

    private fun changeInfo(){
        binding.constraintChangeInfo.setOnClickListener {
            startActivity(Intent(this, AddInformationActivity::class.java))
        }
    }

    private fun logOut(){
        binding.constraintLogout.setOnClickListener {
            val logoutDialog = LogoutDialog(this)
            logoutDialog.show()
        }
    }
}