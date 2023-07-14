package com.example.habday_android.src.main.list.myparticipation

import com.example.habday_android.src.main.list.myparticipation.model.MyParticipationFundingResponse

interface MyParticipationView {
    fun onGetMyParticipationListSuccess(response: MyParticipationFundingResponse)

    fun onGetMyParticipationListFailure(message: String)
}