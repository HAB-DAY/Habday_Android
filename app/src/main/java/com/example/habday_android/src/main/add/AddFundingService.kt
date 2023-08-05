package com.example.habday_android.src.main.add

import android.util.Log
import com.example.habday_android.config.ApplicationClass
import com.example.habday_android.src.main.add.model.AddFundingResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFundingService(val addFundingView: AddFundingView) {
    fun tryAddFunding(fundingItemImg: MultipartBody.Part, dto: RequestBody){
        val addFundingInterface = ApplicationClass.sRetrofit.create(AddFundingInterface::class.java)
        addFundingInterface.addFunding(fundingItemImg, dto).enqueue(object: Callback<AddFundingResponse>{
            override fun onResponse(
                call: Call<AddFundingResponse>,
                response: Response<AddFundingResponse>
            ) {
                //addFundingView.onPostAddFundingSuccess(response.body() as AddFundingResponse)
                Log.d("AddFundingResponse", response.body().toString())
            }

            override fun onFailure(call: Call<AddFundingResponse>, t: Throwable) {
                addFundingView.onPostAddFundingFailure(t.message ?: "통신 오류")
            }

        })
    }
}