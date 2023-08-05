package com.example.habday_android.src.main

import com.example.habday_android.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserService(val userView: UserView) {
    fun tryGetUserInfo(){
        val userInterface = ApplicationClass.sRetrofit.create(UserInterface::class.java)
        userInterface.getUserInfo().enqueue(object : Callback<UserInfoResponse>{
            override fun onResponse(
                call: Call<UserInfoResponse>,
                response: Response<UserInfoResponse>
            ) {
                userView.getUserInfoSuccess(response.body() as UserInfoResponse)
            }

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                userView.getUserInfoFailure(t.message ?: "통신 오류")
            }

        })
    }
}