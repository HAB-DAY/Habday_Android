package com.example.habday_android.src.login.addinfo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityAddInformationBinding
import com.example.habday_android.src.main.MainActivity

class AddInformationActivity : BaseActivity<ActivityAddInformationBinding>(ActivityAddInformationBinding::inflate), AddInfoView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        finishAddInformation()
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
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        //finish()
    }

    override fun addUserInfoFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("정보 등록에 실패했습니다")
    }

}