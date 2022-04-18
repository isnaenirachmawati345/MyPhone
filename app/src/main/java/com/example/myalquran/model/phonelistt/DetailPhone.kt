package com.example.myalquran.model.phonelistt

import com.google.gson.annotations.SerializedName

data class DetailPhone(
    @SerializedName("brand")
    val brand: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("phone name")
    val phone_name: String,
    @SerializedName("slug")
    val slug: String
)