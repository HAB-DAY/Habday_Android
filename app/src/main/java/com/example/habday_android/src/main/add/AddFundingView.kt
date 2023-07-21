package com.example.habday_android.src.main.add

import com.example.habday_android.src.main.add.model.AddFundingResponse

interface AddFundingView {
    fun onPostAddFundingSuccess(response: AddFundingResponse)
    fun onPostAddFundingFailure(message: String)
}