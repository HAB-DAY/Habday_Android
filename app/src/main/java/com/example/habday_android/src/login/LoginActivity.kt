package com.example.habday_android.src.login

import android.content.Intent
import android.os.Bundle
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityLoginBinding
import com.example.habday_android.src.main.MainActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        naverLogin()
    }

    private fun naverLogin(){
        binding.viewNaverLogin.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()
        }
    }
}