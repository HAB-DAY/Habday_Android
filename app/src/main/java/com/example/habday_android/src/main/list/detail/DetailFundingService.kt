package com.example.habday_android.src.main.list.detail

import com.example.habday_android.config.ApplicationClass
import com.example.habday_android.src.main.list.detail.model.DetailFundingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFundingService(val detailFundingView: DetailFundingView) {
    fun tryGetDetailFunding(itemId: Int){
        val detailInterface = ApplicationClass.sRetrofit.create(DetailFundingInterface::class.java)
        detailInterface.getFundingDetail(itemId).enqueue(object : Callback<DetailFundingResponse>{
            override fun onResponse(
                call: Call<DetailFundingResponse>,
                response: Response<DetailFundingResponse>
            ) {
                detailFundingView.onGetDetailFundingSuccess(response.body() as DetailFundingResponse)
            }

            override fun onFailure(call: Call<DetailFundingResponse>, t: Throwable) {
                detailFundingView.onGetDetailFundingFailure(t.message ?: "통신 오류")
            }

        })
    }
}