package com.example.habday_android.src.main.list.detail.delete

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Path

interface DeleteFundingInterface {
    @DELETE("/funding/delete/{fundingItemId}")
    fun deleteFunding(
        @Path("fundingItemId") fundingItemId: Int
    ):Call<DeleteFundingResponse>
}