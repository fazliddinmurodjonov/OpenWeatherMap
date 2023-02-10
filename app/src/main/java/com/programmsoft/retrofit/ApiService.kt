package com.programmsoft.retrofit

import com.programmsoft.model.OpenWeatherMap
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import javax.security.auth.callback.Callback

interface ApiService {
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appid") appId: String,
    ): Call<OpenWeatherMap>
}