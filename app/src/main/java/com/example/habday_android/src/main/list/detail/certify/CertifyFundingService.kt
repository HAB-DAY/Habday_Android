package com.example.habday_android.src.main.list.detail.certify

import com.example.habday_android.config.ApplicationClass
import com.example.habday_android.src.main.list.detail.certify.model.CertifyFundingFailureResponse
import com.example.habday_android.src.main.list.detail.certify.model.CertifyFundingSuccessResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CertifyFundingService(val certifyFundingView: CertifyFundingView) {
    fun tryCertifyFunding(img: MultipartBody.Part, dto: RequestBody){
        val certifyFundingInterface = ApplicationClass.sRetrofit.create(CertifyFundingInterface::class.java)
        certifyFundingInterface.postCertify(img, dto).enqueue(object : Callback<CertifyFundingSuccessResponse>{
            override fun onResponse(
                call: Call<CertifyFundingSuccessResponse>,
                response: Response<CertifyFundingSuccessResponse>
            ) {
                certifyFundingView.onPostCertifyFundingSuccess(response.body() as CertifyFundingSuccessResponse)
            }

            override fun onFailure(call: Call<CertifyFundingSuccessResponse>, t: Throwable) {
                certifyFundingView.onPostCertifyFundingFailure(t.message ?: "통신 오류")
            }


        })
    }
}