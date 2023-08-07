package com.example.habday_android.src.login.addinfo.getToken

data class GetTokenResponse(
    val accessToken: String,
    val message: String,
    val refreshToken: String,
    val status: String
)