package com.example.habday_android.src.main.list.progressingfunding

import com.example.habday_android.config.ApplicationClass
import com.example.habday_android.src.main.list.ListInterface
import com.example.habday_android.src.main.list.progressingfunding.model.ProgressingFundingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProgressingFundingService(val progressingFundingView: ProgressingFundingView) {

    fun tryGetProgressingFundingList(memberId: Long, lastItemId: Long?){
        val listInterface = ApplicationClass.sRetrofit.create(ListInterface::class.java)
        listInterface.getProgressingFunding(memberId, lastItemId).enqueue(object: Callback<ProgressingFundingResponse>{
            override fun onResponse(
                call: Call<ProgressingFundingResponse>,
                response: Response<ProgressingFundingResponse>
            ) {
                progressingFundingView.getProgressingFundingListSuccess(response.body() as ProgressingFundingResponse)
            }

            override fun onFailure(call: Call<ProgressingFundingResponse>, t: Throwable) {
                progressingFundingView.getProgressingFundingListFailure(t.message ?: "통신 오류")
            }

        })
    }
}