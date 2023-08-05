package com.example.habday_android.src.login.addinfo

interface AddInfoView {
    fun addUserInfoSuccess(response: AddUserInfoResponse)
    fun addUserInfoFailure(message: String)
}