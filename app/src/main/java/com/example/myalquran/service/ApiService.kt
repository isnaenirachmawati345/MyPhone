package com.example.myalquran.service

import com.example.myalquran.model.phonelistt.GetAllListPhone
import com.example.myalquran.model.spesifikasiphone.GetAllSpesifikasi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("https://api-mobilespecs.azharimm.site/v2/brands/apple-phones-48?page=2")
    fun getListPhones(): Call<GetAllListPhone>

    @GET(" /v2/brands/{brand_slug}/{phone_slug}")
    fun getPhoneSpecification(@Path("phone_id")phoneid:Int): Call<GetAllSpesifikasi>


}