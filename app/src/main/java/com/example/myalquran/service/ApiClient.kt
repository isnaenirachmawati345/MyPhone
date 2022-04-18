package com.example.myalquran.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL ="https://api-mobilespecs.azharimm.site/v2/brands/apple-phones-48?page=2"
    private val logging: HttpLoggingInterceptor
    get() {
       val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        }
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(ApiService::class.java)
    }
}