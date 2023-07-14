package com.example.habday_android.src.main.list.progressingfunding

import com.example.habday_android.src.main.list.progressingfunding.model.ProgressingFundingResponse

interface ProgressingFundingView {
    fun getProgressingFundingListSuccess(response: ProgressingFundingResponse)

    fun getProgressingFundingListFailure(message: String)
}