package com.example.habday_android.src.login.addinfo

import com.google.gson.annotations.SerializedName

data class AddUserInfoReq(
    @SerializedName("birthday")
    val birthday: String,

    @SerializedName("accountName")
    val accountName: String,

    @SerializedName("account")
    val account: String
)
