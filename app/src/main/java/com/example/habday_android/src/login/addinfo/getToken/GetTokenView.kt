package com.example.habday_android.src.login.addinfo.getToken

interface GetTokenView {
    fun getTokenSuccess(response: GetTokenResponse)
    fun getTokenFailure(message: String)
}