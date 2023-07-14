package com.example.habday_android.src.main.list.finish

import com.example.habday_android.config.ApplicationClass
import com.example.habday_android.src.main.list.ListInterface
import com.example.habday_android.src.main.list.finish.model.FinishFundingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FinishFundingService(val listView: FinishFundingView) {

    fun tryGetFinishFundingList(memberId: Long, lastItemId: Long?){
        val listInterface = ApplicationClass.sRetrofit.create(ListInterface::class.java)
        listInterface.getFinishedFunding(memberId, lastItemId).enqueue(object: Callback<FinishFundingResponse>{
            override fun onResponse(
                call: Call<FinishFundingResponse>,
                response: Response<FinishFundingResponse>
            ) {
                listView.onGetFinishFundingListSuccess(response.body() as FinishFundingResponse)
            }

            override fun onFailure(call: Call<FinishFundingResponse>, t: Throwable) {
                listView.onGetFinishFundingListFailure(t.message ?: "통신 오류")
            }

        })
    }
}