package com.programmsoft.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiClient {
    private const val baseUrl = "https://api.openweathermap.org/"
    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()
    private const val BASE_URL = baseUrl
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}