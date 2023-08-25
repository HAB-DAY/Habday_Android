package com.example.habday_android.src.main.list.detail.viewcertify

import com.example.habday_android.src.main.list.detail.viewcertify.model.ViewCertifyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ViewCertifyInterface {
    @GET("/funding/showConfirmation/{fundingItemId}")
    fun getShowConfirmation(
        @Path("fundingItemId") fundingItemId: Int
    ): Call<ViewCertifyResponse>
}