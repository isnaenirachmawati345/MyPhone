package com.example.myalquran.model.spesifikasiphone

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("brand")
    val brand: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("os")
    val os: String,
    @SerializedName("phone images")
    val phone_images: List<String>,
    @SerializedName("phone name")
    val phone_name: String,
    @SerializedName("release date")
    val release_date: String,
    @SerializedName("specifications")
    val specifications: List<Specification>,
    @SerializedName("storage")
    val storage: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)