package com.example.habday_android.src.main.list.detail.modify

import android.util.Log
import com.example.habday_android.config.ApplicationClass
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModifyFundingService(val modifyFundingView: ModifyFundingView) {
    fun tryModifyFunding(fundingItemId: Int, fundingItemImg: MultipartBody.Part?, data: HashMap<String, RequestBody>){
        val modifyFundingInterface = ApplicationClass.sRetrofit.create(ModifyFundingInterface::class.java)
        modifyFundingInterface.modifyFunding(fundingItemId, fundingItemImg, data).enqueue(object: Callback<ModifyFundingResponse>{
            override fun onResponse(
                call: Call<ModifyFundingResponse>,
                response: Response<ModifyFundingResponse>
            ) {
                modifyFundingView.onPutModifyFundingSuccess(response.body() as ModifyFundingResponse)
            }

            override fun onFailure(call: Call<ModifyFundingResponse>, t: Throwable) {
                modifyFundingView.onPutModifyFundingFailure(t.message ?: "통신 오류")
            }

        })
    }
}