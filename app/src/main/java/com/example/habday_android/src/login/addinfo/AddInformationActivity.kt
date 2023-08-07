package com.example.habday_android.src.login.addinfo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.habday_android.config.ApplicationClass.Companion.editor
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityAddInformationBinding
import com.example.habday_android.src.login.addinfo.getToken.GetTokenResponse
import com.example.habday_android.src.login.addinfo.getToken.GetTokenService
import com.example.habday_android.src.login.addinfo.getToken.GetTokenView
import com.example.habday_android.src.main.MainActivity

class AddInformationActivity : BaseActivity<ActivityAddInformationBinding>(ActivityAddInformationBinding::inflate), AddInfoView, GetTokenView{
    private var code : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getCode()
        finishAddInformation()
    }

    private fun getCode(){
        code = intent.getStringExtra("code")
        Log.d("addinfocode", code!!)

        showLoadingDialog(this)
        GetTokenService(this).tryGetToken(code!!)
    }

    override fun getTokenSuccess(response: GetTokenResponse) {
        dismissLoadingDialog()

        editor.putString("accessToken", response.accessToken)
        editor.commit()
    }

    override fun getTokenFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("토큰 발급에 실패했습니다")
    }

    private fun finishAddInformation(){
        val birthDay = binding.etAddInformationBirthday.text
        val accountName = binding.etAddInformationBankName.text
        val account = binding.etAddInformationBankNumber.text


        binding.tvStart.setOnClickListener {
            showLoadingDialog(this)
            AddInfoService(this).tryPutUserInfo(AddUserInfoReq(birthDay.toString(), accountName.toString(), account.toString()))
        }
    }

    override fun addUserInfoSuccess(response: AddUserInfoResponse) {
        dismissLoadingDialog()

        intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    override fun addUserInfoFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("정보 등록에 실패했습니다")
    }

}