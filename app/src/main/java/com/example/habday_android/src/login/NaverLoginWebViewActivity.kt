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
import com.example.habday_android.config.ApplicationClass
import com.example.habday_android.config.ApplicationClass.Companion.editor
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityNaverLoginWebViewBinding
import com.example.habday_android.src.login.addinfo.AddInformationActivity
import com.example.habday_android.src.login.addinfo.getToken.GetTokenResponse
import com.example.habday_android.src.login.addinfo.getToken.GetTokenService
import com.example.habday_android.src.login.addinfo.getToken.GetTokenView
import com.example.habday_android.src.login.checkMember.CheckMemberResponse
import com.example.habday_android.src.login.checkMember.CheckMemberService
import com.example.habday_android.src.login.checkMember.CheckMemberView
import com.example.habday_android.src.main.MainActivity

class NaverLoginWebViewActivity : BaseActivity<ActivityNaverLoginWebViewBinding>(ActivityNaverLoginWebViewBinding::inflate), CheckMemberView, GetTokenView {
    var flag = false
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

            Log.d("okhttp_nowUrl", nowUrl.toString())
            code = nowUrl?.substring(nowUrl.toString().length - 18)
            Log.d("okhttp_code", code.toString())

            // api 호출
            CheckMemberService(this@NaverLoginWebViewActivity).tryGetCheckMember()
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

    override fun getCheckMemberSuccess(response: CheckMemberResponse) {
        // 이미 가입된 사용자
        // 토큰 발급 받은 후 성공하면 메인으로
        if(!code?.startsWith("response")!!){
            Log.d("okhttp_member_code", "success")
            GetTokenService(this).tryGetToken(code!!)
        }
    }

    override fun getCheckMemberFailure(message: String) {
        // 새로운 가입
        intent = Intent(this@NaverLoginWebViewActivity, AddInformationActivity::class.java)
        intent.putExtra("code", code)
        startActivity(intent)
        finish()
    }

    override fun getTokenSuccess(response: GetTokenResponse) {
        // 토큰 발급 성공
        editor.putString("accessToken", response.accessToken)
        editor.commit()

        intent = Intent(this@NaverLoginWebViewActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    override fun getTokenFailure(message: String) {
        showCustomToast("토큰 발급에 실패했습니다")
    }


}