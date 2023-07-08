package com.example.habday_android.src.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityAddInformationBinding
import com.example.habday_android.src.main.MainActivity

class AddInformationActivity : BaseActivity<ActivityAddInformationBinding>(ActivityAddInformationBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        finishAddInformation()
    }

    private fun finishAddInformation(){
        binding.tvStart.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }
}