package com.example.habday_android.src.main.list.detail

import com.example.habday_android.src.main.list.detail.model.DetailFundingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailFundingInterface {
    @GET("/funding/showFundingContent")
    fun getFundingDetail(
        @Query("itemId") itemId: Int
    ): Call<DetailFundingResponse>
}