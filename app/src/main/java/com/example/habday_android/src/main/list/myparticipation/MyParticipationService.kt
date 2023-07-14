package com.example.habday_android.src.main.list.myparticipation

import com.example.habday_android.config.ApplicationClass
import com.example.habday_android.src.main.list.ListInterface
import com.example.habday_android.src.main.list.myparticipation.model.MyParticipationFundingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyParticipationService(val myParticipationView: MyParticipationView) {
    fun tryGetMyParticipationFundingList(memberId: Long, lastItemId: Long?){
        val listInterface = ApplicationClass.sRetrofit.create(ListInterface::class.java)
        listInterface.getParticipatedFunding(memberId, lastItemId).enqueue(object: Callback<MyParticipationFundingResponse>{
            override fun onResponse(
                call: Call<MyParticipationFundingResponse>,
                response: Response<MyParticipationFundingResponse>
            ) {
                myParticipationView.onGetMyParticipationListSuccess(response.body() as MyParticipationFundingResponse)
            }

            override fun onFailure(call: Call<MyParticipationFundingResponse>, t: Throwable) {
                myParticipationView.onGetMyParticipationListFailure(t.message ?: "통신 오류")
            }

        })
    }
}