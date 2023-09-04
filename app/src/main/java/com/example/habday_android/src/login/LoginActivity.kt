package com.example.habday_android.src.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.habday_android.config.ApplicationClass.Companion.sSharedPreferences
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityLoginBinding
import com.example.habday_android.src.login.addinfo.AddInformationActivity
import com.example.habday_android.src.main.MainActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        naverLogin()
    }

    private fun naverLogin(){
        binding.viewNaverLogin.setOnClickListener {
            val token = sSharedPreferences.getString("accessToken", null)
            if(token.isNullOrEmpty()){
                // accessToken 없는 경우

                intent = Intent(this, NaverLoginWebViewActivity::class.java)
                startActivity(intent)
                finish()

            }
            else {
                // token 있는 경우
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}