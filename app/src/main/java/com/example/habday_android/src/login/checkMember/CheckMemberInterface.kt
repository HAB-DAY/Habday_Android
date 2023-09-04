package com.example.habday_android.src.login.checkMember

import retrofit2.Call
import retrofit2.http.GET

interface CheckMemberInterface {
    @GET("/check/memberProfile")
    fun getCheckMember(): Call<CheckMemberResponse>
}