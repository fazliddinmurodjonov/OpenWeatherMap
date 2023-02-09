package com.programmsoft.openweathermap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.programmsoft.openweathermap.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity(R.layout.activity_weather) {
    private val binding: ActivityWeatherBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
    }
}