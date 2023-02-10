package com.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.programmsoft.model.OpenWeatherMap
import com.programmsoft.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherModel : ViewModel() {
    var getWeatherData: MutableLiveData<OpenWeatherMap> = MutableLiveData()
    fun getWeather(lat: Double, long: Double, appId: String): LiveData<OpenWeatherMap> {
        ApiClient.apiService.getWeather(lat, long, appId)
            .enqueue(object : Callback<OpenWeatherMap> {
                override fun onResponse(
                    call: Call<OpenWeatherMap>,
                    response: Response<OpenWeatherMap>,
                ) {
                    if (response.isSuccessful) {
                        getWeatherData.value = response.body()
                    }
                }
                override fun onFailure(call: Call<OpenWeatherMap>, t: Throwable) {
                }

            })
        return getWeatherData
    }
}