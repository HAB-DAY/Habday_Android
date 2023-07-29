package com.example.habday_android.src.main.list.detail.modify

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path

interface ModifyFundingInterface {
    @Multipart
    @PUT("/funding/update/{fundingItemId}")
    fun modifyFunding(
        @Path("fundingItemId") fundingItemId: Int,
        @Part fundingItemImg: MultipartBody.Part?,
        @PartMap data: HashMap<String, RequestBody>
    ):Call<ModifyFundingResponse>
}