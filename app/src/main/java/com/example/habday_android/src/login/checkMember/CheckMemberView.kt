package com.example.habday_android.src.login.checkMember

interface CheckMemberView {
    fun getCheckMemberSuccess(response: CheckMemberResponse)
    fun getCheckMemberFailure(message: String)
}