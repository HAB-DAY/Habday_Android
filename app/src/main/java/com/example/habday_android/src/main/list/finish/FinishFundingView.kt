package com.example.habday_android.src.main.list.finish

import com.example.habday_android.src.main.list.finish.model.FinishFundingResponse

interface FinishFundingView {
    fun onGetFinishFundingListSuccess(response: FinishFundingResponse)

    fun onGetFinishFundingListFailure(message: String)
}