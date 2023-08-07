package com.example.habday_android.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.habday_android.config.ApplicationClass.Companion.editor
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivitySplashBinding
import com.example.habday_android.src.login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 임시로 토큰 저장
        //editor.putString("accessToken", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjWVJsWTJzRDdoYjZXZlhMVzJTVG9MZlg2QWtHa08xZUFKTlQ0cXhjdnNZIiwibmlja25hbWUiOiJjWVJsWTJzRDdoYjZXZlhMVzJTVG9MZlg2QWtHa08xZUFKTlQ0cXhjdnNZIiwiaWQiOjUsImV4cCI6MTY4OTgzNjcxOX0.cZkOxE60rPg9k0L4lkmE0VKrKwHMH1_3YnhhQ8yraSd0cMOPIuPDulx2945ziJt9UEwudFLoD1VLShzd3qc2tA");
        //editor.commit()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500)
    }
}