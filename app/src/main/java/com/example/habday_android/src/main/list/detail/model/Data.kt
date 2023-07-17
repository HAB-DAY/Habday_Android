package com.example.habday_android.src.main.list.detail.model

data class Data(
    val finishDate: String,
    val fundDetail: String,
    val fundingItemImg: String,
    val fundingName: String,
    val fundingParticipantList: List<FundingParticipant>,
    val goalPrice: Double,
    val hostName: String,
    val itemPrice: Double,
    val percentage: Int,
    val startDate: String,
    val status: String,
    val totalPrice: Double
)