package com.example.myalquran.model.spesifikasiphone

import com.google.gson.annotations.SerializedName

data class Spec(
    @SerializedName("key")
    val key: String,
    @SerializedName("val")
    val `val`: List<String>
)