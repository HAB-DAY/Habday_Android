package com.example.habday_android.src.main

import retrofit2.Call
import retrofit2.http.GET

interface UserInterface {
    @GET("/funding/showBirthdayLeftDay")
    fun getUserInfo(): Call<UserInfoResponse>
}