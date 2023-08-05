package com.example.habday_android.src.main

interface UserView {
    fun getUserInfoSuccess(response: UserInfoResponse)
    fun getUserInfoFailure(message: String)
}