package com.example.habday_android.src.login.addinfo

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT

interface AddInfoInterface {
    @PUT("/save/memberProfile")
    fun putUserInfo(
        @Body data: AddUserInfoReq
    ): Call<AddUserInfoResponse>
}