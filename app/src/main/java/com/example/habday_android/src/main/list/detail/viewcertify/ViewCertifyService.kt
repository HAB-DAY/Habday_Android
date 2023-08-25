package com.example.habday_android.src.main.list.detail.viewcertify

import com.example.habday_android.config.ApplicationClass
import com.example.habday_android.src.main.list.detail.viewcertify.model.ViewCertifyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewCertifyService(val viewCertifyView: ViewCertifyView) {
    fun tryGetConfirmation(fundingItemId: Int){
        val viewCertifyInterface = ApplicationClass.sRetrofit.create(ViewCertifyInterface::class.java)
        viewCertifyInterface.getShowConfirmation(fundingItemId).enqueue(object: Callback<ViewCertifyResponse>{
            override fun onResponse(
                call: Call<ViewCertifyResponse>,
                response: Response<ViewCertifyResponse>
            ) {
                viewCertifyView.tryGetConfirmationSuccess(response.body() as ViewCertifyResponse)
            }

            override fun onFailure(call: Call<ViewCertifyResponse>, t: Throwable) {
                viewCertifyView.tryGetConfirmationFailure(t.message ?: "통신 오류")
            }

        })
    }
}