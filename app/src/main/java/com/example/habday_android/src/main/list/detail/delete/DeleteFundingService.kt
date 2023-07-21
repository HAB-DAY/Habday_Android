package com.example.habday_android.src.main.list.detail.delete

import com.example.habday_android.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteFundingService(val deleteFundingView: DeleteFundingView) {
    fun tryDeleteFunding(fundingItemId: Int){
        val deleteFundingInterface = ApplicationClass.sRetrofit.create(DeleteFundingInterface::class.java)
        deleteFundingInterface.deleteFunding(fundingItemId).enqueue(object: Callback<DeleteFundingResponse>{
            override fun onResponse(
                call: Call<DeleteFundingResponse>,
                response: Response<DeleteFundingResponse>
            ) {
                deleteFundingView.deleteFundingSuccess(response.body() as DeleteFundingResponse)
            }

            override fun onFailure(call: Call<DeleteFundingResponse>, t: Throwable) {
                deleteFundingView.deleteFundingFailure(t.message ?: "통신 오류")
            }

        })
    }
}