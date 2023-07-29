package com.example.habday_android.src.main.list.detail.modify

interface ModifyFundingView {
    fun onPutModifyFundingSuccess(response: ModifyFundingResponse)
    fun onPutModifyFundingFailure(message: String)
}