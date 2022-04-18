package com.example.myalquran.model.spesifikasiphone

import com.google.gson.annotations.SerializedName

data class GetAllSpesifikasi(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("spesifikasi")
    val spec: Spec
)