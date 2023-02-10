package com.programmsoft.openweathermap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.programmsoft.openweathermap.databinding.ActivityWeatherBinding
import com.viewModel.WeatherModel
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity(R.layout.activity_weather) {
    private val binding: ActivityWeatherBinding by viewBinding()
    lateinit var weatherModel: WeatherModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        val lat = intent.getDoubleExtra("lat", 0.0)
        val lon = intent.getDoubleExtra("lon", 0.0)
        val appId = intent.getStringExtra("appId")
        weatherModel = ViewModelProvider(this)[WeatherModel::class.java]
        weatherModel.getWeather(lat, lon, appId!!).observe(this) {

            with(binding)
            {
                temperature.visibility = View.VISIBLE
                celsius.visibility = View.VISIBLE
                informationLayout.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
                temperature.text = ((it.main.temp_max - 273.15).toInt()).toString()
                city.text = "City        : ${it.name}"
                latitude.text = "Latitude    : ${it.coord.lat}°"
                longitude.text = "Longitude   : ${it.coord.lon}°"
                description.text = "Description : ${it.weather[0].description}"
                pressure.text = "Pressure    : ${it.main.pressure}"
                humidity.text = "Humidity    : ${it.main.humidity}"
                windSpeed.text = "Wind Speed  : ${it.wind.speed}"
                windDegree.text = "Wind Degree : ${it.wind.deg}"
                cloud.text = "Cloud       : ${it.clouds.all}"
                sunrise.text = "Sunrise     : ${getSunRiseSet(it.sys.sunrise.toLong())}"
                sunset.text  = "Sunset      : ${getSunRiseSet(it.sys.sunset.toLong())}"
            }


        }


    }

    private fun getSunRiseSet(millisecond: Long): String {
        val millis = millisecond * 1000
        val date = Date(millis)
        val format = SimpleDateFormat("HH:mm")
        return format.format(date)
    }
}