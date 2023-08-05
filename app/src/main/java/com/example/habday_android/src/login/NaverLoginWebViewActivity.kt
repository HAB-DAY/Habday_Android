package com.example.habday_android.src.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebBackForwardList
import android.webkit.WebViewClient
import com.example.habday_android.R
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityNaverLoginWebViewBinding

class NaverLoginWebViewActivity : BaseActivity<ActivityNaverLoginWebViewBinding>(ActivityNaverLoginWebViewBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.webViewNaverLogin.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

        binding.webViewNaverLogin.loadUrl("https://nid.naver.com/oauth2.0/authorize?client_id=UAusWFQ9IPtJbSu2FD8R&redirect_uri=http://localhost:8080/login/oauth2/code/naver&response_type=code")
        //binding.webViewNaverLogin.loadUrl("https://naver.co.kr")

        //var webBackForwardList: WebBackForwardList = binding.webViewNaverLogin.copyBackForwardList()
        //var prevIndex = webBackForwardList.currentIndex - 1;
        //var backUrl = webBackForwardList.getItemAtIndex(prevIndex).url
        var backUrl = binding.webViewNaverLogin.url
        Log.d("backUrl", backUrl.toString())

    }

    override fun onBackPressed() {
        // 뒤로 가기 버튼 눌렀을 때 웹 페이지 내에서 뒤로 가기가 있으면 해주고 아니면, 앱 뒤로가기 실행
        if(binding.webViewNaverLogin.canGoBack()){
            binding.webViewNaverLogin.goBack()
        }else{
            finish()
        }
    }


}