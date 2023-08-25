package com.example.habday_android.src.main.list.detail.viewcertify

import com.example.habday_android.src.main.list.detail.viewcertify.model.ViewCertifyResponse

interface ViewCertifyView {
    fun tryGetConfirmationSuccess(response: ViewCertifyResponse)
    fun tryGetConfirmationFailure(message: String)
}