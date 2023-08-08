package com.example.habday_android.src.login.addinfo

import android.app.DatePickerDialog
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
import java.text.SimpleDateFormat
import java.util.Calendar

class AddInformationActivity : BaseActivity<ActivityAddInformationBinding>(ActivityAddInformationBinding::inflate), AddInfoView, GetTokenView{
    private var code : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getCode()
        getDatePicker()
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

    private fun getDatePicker(){
        binding.ivCalendar.setOnClickListener {
            var dateString = ""

            val cal = Calendar.getInstance()

            val dateSetListener = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                var months = ""
                months = if(month+1 < 10){
                    "0" + (month+1)
                }else{
                    (month+1).toString()
                }

                var days = ""
                days = if(dayOfMonth < 10){
                    "0$dayOfMonth"
                }else{
                    dayOfMonth.toString()
                }


                dateString = "${year}년 ${months}월 ${days}일"
                binding.etAddInformationBirthday.text = dateString
            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun finishAddInformation(){
        val birthDay = binding.etAddInformationBirthday.text.toString()
        val accountName = binding.etAddInformationBankName.text.toString()
        val account = binding.etAddInformationBankNumber.text.toString()


        binding.tvStart.setOnClickListener {
            showLoadingDialog(this)
            AddInfoService(this).tryPutUserInfo(AddUserInfoReq(birthDay, accountName, account))
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