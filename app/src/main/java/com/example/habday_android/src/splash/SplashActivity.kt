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
        //editor.putString("accessToken", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFMkVIYkFxVWw1aksxSUZac0dsQnFFRURCUjZaTkg3Mmp1MEdVc25YT2Q0Iiwibmlja25hbWUiOiJFMkVIYkFxVWw1aksxSUZac0dsQnFFRURCUjZaTkg3Mmp1MEdVc25YT2Q0IiwiaWQiOjgsImV4cCI6MTY5MTQ2NDI1MH0.XTKJFr_lxE4jqq1w4kQXPiIoqcg_cu_jCf3aZaqhS_MD51BrvphQJw1W6Zlcj2TCgfg3KRTCoXnpTt1Fk9rj3A");
        //editor.commit()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500)
    }
}