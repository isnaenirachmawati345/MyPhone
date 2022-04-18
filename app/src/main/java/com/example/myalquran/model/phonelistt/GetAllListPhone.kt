package com.example.myalquran.model.phonelistt

import com.google.gson.annotations.SerializedName

data class GetAllListPhone(
    @SerializedName("data")
    val `data`: DataPhone,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("detail")
    val detailPhone: List<DetailPhone>
)
