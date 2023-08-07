package com.example.habday_android.src.login.addinfo.getToken

import com.example.habday_android.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetTokenService(val getTokenView: GetTokenView) {
    fun tryGetToken(code: String){
        val tokenInterface = ApplicationClass.sRetrofit.create(GetTokenInterface::class.java)
        tokenInterface.getToken(code).enqueue(object: Callback<GetTokenResponse>{
            override fun onResponse(
                call: Call<GetTokenResponse>,
                response: Response<GetTokenResponse>
            ) {
                getTokenView.getTokenSuccess(response.body() as GetTokenResponse)
            }

            override fun onFailure(call: Call<GetTokenResponse>, t: Throwable) {
                getTokenView.getTokenFailure(t.message ?: "통신 오류")
            }

        })
    }
}