package com.example.habday_android.src.main.list.detail.certify

import com.example.habday_android.src.main.list.detail.certify.model.CertifyFundingSuccessResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CertifyFundingInterface {
    @Multipart
    @POST("/funding/confirm/{memberId}")
    fun postCertify(
        @Part img: MultipartBody.Part,
        @Part("dto") dto: RequestBody
    ): Call<CertifyFundingSuccessResponse>
}