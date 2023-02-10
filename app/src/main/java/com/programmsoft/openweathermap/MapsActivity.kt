package com.programmsoft.openweathermap

import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.programmsoft.openweathermap.databinding.ActivityMapsBinding
import java.io.IOException
import java.util.*

class MapsActivity : AppCompatActivity(R.layout.activity_maps), OnMapReadyCallback {
    private val binding: ActivityMapsBinding by viewBinding()
    private lateinit var mMap: GoogleMap
    val geocoder = Geocoder(this, Locale.getDefault())
    var appId = "8cbe9357bad1298df6b6debaa8d214e1"
    var lat: Double = 0.0
    var lon: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.nextBtn.setOnClickListener {
            if (lat != 0.0 && lon != 0.0) {
                val intent = Intent(this, WeatherActivity::class.java)
                intent.putExtra("appId", appId)
                intent.putExtra("lat", lat)
                intent.putExtra("lon", lon)
                startActivity(intent)
            }

        }

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var marker: Marker? = null
        mMap.setOnMapClickListener { latLng ->
            lat = latLng.latitude
            lon = latLng.longitude
            if (marker != null) {
                marker?.remove()
            }
            var locationAddress = ""
            val geocoder = Geocoder(applicationContext, Locale.getDefault())
            try {
                val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                if (addresses != null && addresses.size > 0) {
                    val address = addresses[0].getAddressLine(0)
                    //    Log.d("Map", "Address: $address")
                    locationAddress = address
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            marker = googleMap.addMarker(MarkerOptions().position(latLng).title(locationAddress))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        }
    }
}