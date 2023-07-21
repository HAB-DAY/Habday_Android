package com.example.habday_android.src.main.add

import com.example.habday_android.src.main.add.model.AddFundingResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AddFundingInterface {

    @Multipart
    @POST("/create/fundingItem")
    fun addFunding(
        @Part fundingItemImg: MultipartBody.Part,
        @PartMap dto: HashMap<String, RequestBody>
    ):Call<AddFundingResponse>
}