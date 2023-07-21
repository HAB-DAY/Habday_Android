package com.example.habday_android.src.main.list.detail

import com.example.habday_android.src.main.list.detail.model.DetailFundingResponse

interface DetailFundingView {
    fun onGetDetailFundingSuccess(response: DetailFundingResponse)
    fun onGetDetailFundingFailure(message: String)
}