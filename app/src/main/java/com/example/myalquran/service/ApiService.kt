package com.example.myalquran.service

import com.example.myalquran.model.phonelistt.GetAllListPhone
import com.example.myalquran.model.spesifikasiphone.GetAllSpesifikasi
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("https://api-mobilespecs.azharimm.site/v2/brands/apple-phones-48?page=2")
    fun getListPhones(): Call<GetAllListPhone>

    @GET("https://api-mobilespecs.azharimm.site/v2/apple_iphone_12_pro_max-10237")
    fun getPhoneSpecification(): Call<GetAllSpesifikasi>
}