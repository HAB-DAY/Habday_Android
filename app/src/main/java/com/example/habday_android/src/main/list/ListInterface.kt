package com.example.habday_android.src.main.list

import com.example.habday_android.src.main.list.finish.model.FinishFundingResponse
import com.example.habday_android.src.main.list.progressingfunding.model.ProgressingFundingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ListInterface {
    // 호스팅한 펀딩 리스트 - 완료
    @GET("/funding/itemList/hosted/finished")
    fun getFinishedFunding(
        @Query("memberId") memberId: Long,
        @Query("lastItemId") lastItemId: Long?
    ): Call<FinishFundingResponse>

    // 호스팅한 펀딩 리스트 - 진행중
    @GET("/funding/itemList/hosted/progress")
    fun getProgressingFunding(
        @Query("memberId") memberId: Long,
        @Query("lastItemId") lastItemId: Long?
    ): Call<ProgressingFundingResponse>
}