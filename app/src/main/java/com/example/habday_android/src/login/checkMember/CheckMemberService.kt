package com.example.habday_android.src.login.checkMember

import com.example.habday_android.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckMemberService(val checkMemberView: CheckMemberView) {
    fun tryGetCheckMember(){
        val checkInterface = ApplicationClass.sRetrofit.create(CheckMemberInterface::class.java)
        checkInterface.getCheckMember().enqueue(object: Callback<CheckMemberResponse>{
            override fun onResponse(
                call: Call<CheckMemberResponse>,
                response: Response<CheckMemberResponse>
            ) {
                checkMemberView.getCheckMemberSuccess(response.body() as CheckMemberResponse)
            }

            override fun onFailure(call: Call<CheckMemberResponse>, t: Throwable) {
                checkMemberView.getCheckMemberFailure(t.message ?: "통신 오류")
            }

        })
    }
}