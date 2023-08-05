package com.example.habday_android.src.login.addinfo

import com.example.habday_android.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddInfoService(val addInfoView: AddInfoView) {
    fun tryPutUserInfo(data: AddUserInfoReq){
         val userInterface = ApplicationClass.sRetrofit.create(AddInfoInterface::class.java)
        userInterface.putUserInfo(data).enqueue(object : Callback<AddUserInfoResponse>{
            override fun onResponse(
                call: Call<AddUserInfoResponse>,
                response: Response<AddUserInfoResponse>
            ) {
                addInfoView.addUserInfoSuccess(response.body() as AddUserInfoResponse)
            }

            override fun onFailure(call: Call<AddUserInfoResponse>, t: Throwable) {
                addInfoView.addUserInfoFailure(t.message ?: "통신 오류")
            }

        })
    }
}