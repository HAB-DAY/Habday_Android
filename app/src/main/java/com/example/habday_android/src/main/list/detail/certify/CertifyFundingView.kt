package com.example.habday_android.src.main.list.detail.certify

import com.example.habday_android.src.main.list.detail.certify.model.CertifyFundingFailureResponse
import com.example.habday_android.src.main.list.detail.certify.model.CertifyFundingSuccessResponse

interface CertifyFundingView {
    fun onPostCertifyFundingSuccess(response: CertifyFundingSuccessResponse)
    fun onPostCertifyFundingFailure(response: CertifyFundingFailureResponse)
    //fun onPostCertifyFundingFailure(message: String)
}