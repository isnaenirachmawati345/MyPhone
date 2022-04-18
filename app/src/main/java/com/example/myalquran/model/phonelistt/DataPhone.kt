package com.example.myalquran.model.phonelistt

import com.google.gson.annotations.SerializedName

data class DataPhone(
    @SerializedName("page")
    val current_page: Int,
    @SerializedName("last page")
    val last_page: Int,
    @SerializedName ("phones")
    val phones: List<DetailPhone>,
    @SerializedName("title")
    val title: String
)