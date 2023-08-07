package com.example.habday_android.src.login

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebBackForwardList
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.habday_android.R
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityNaverLoginWebViewBinding
import com.example.habday_android.src.login.addinfo.AddInformationActivity

class NaverLoginWebViewActivity : BaseActivity<ActivityNaverLoginWebViewBinding>(ActivityNaverLoginWebViewBinding::inflate) {
    var flag = true
    var nowUrl : String ?= null
    var code : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.webViewNaverLogin.apply {
            webViewClient = MyWebViewClient()
            settings.javaScriptEnabled = true
        }

        binding.webViewNaverLogin.loadUrl("https://nid.naver.com/oauth2.0/authorize?client_id=UAusWFQ9IPtJbSu2FD8R&redirect_uri=http://localhost:8080/login/oauth2/code/naver&response_type=code")

    }

    inner class MyWebViewClient: WebViewClient() {
        // 페이지가 로딩이 시작하는 시점 콜백
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        // 페이지가 로딩이 끝나는 시점 콜백
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            nowUrl = binding.webViewNaverLogin.url

            if(nowUrl?.startsWith("https://nid.naver.com/oauth2.0/authorize?client_id") == false && flag){
                if(nowUrl?.startsWith("http://localhost:8080") == true){
                    flag = false
                }else{
                    binding.webViewNaverLogin.loadUrl("http://localhost:3000/androidsignup")
                    nowUrl = binding.webViewNaverLogin.url
                }
            }

            nowUrl = binding.webViewNaverLogin.url
            Log.d("backUrl1", nowUrl.toString())

            if(nowUrl?.length!! > 35){
                code = nowUrl?.substring(51,69)
                Log.d("backUrl2", code.toString())

                intent = Intent(this@NaverLoginWebViewActivity, AddInformationActivity::class.java)
                intent.putExtra("code", code)
                startActivity(intent)
                finish()
            }



        }

       // 페이지가 보여지는 시점 콜백
        override fun onPageCommitVisible(view: WebView?, url: String?) {
            super.onPageCommitVisible(view, url)
        }
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