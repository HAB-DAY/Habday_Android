package com.example.habday_android.src.login.addinfo.getToken

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetTokenInterface {
    @GET("/api/oauth/token/naver")
    fun getToken(
        @Query("code") code:String
    ): Call<GetTokenResponse>
}