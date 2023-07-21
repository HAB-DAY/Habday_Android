package com.example.habday_android.src.main.list.detail.delete

interface DeleteFundingView {
    fun deleteFundingSuccess(response: DeleteFundingResponse)
    fun deleteFundingFailure(message: String)
}