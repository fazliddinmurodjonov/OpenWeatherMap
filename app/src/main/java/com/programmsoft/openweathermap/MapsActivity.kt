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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.nextBtn.setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var marker: Marker? = null
        mMap.setOnMapClickListener { latLng ->
            Toast.makeText(this, latLng.latitude.toString(), Toast.LENGTH_SHORT).show()

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
                    Toast.makeText(this, "$address", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            marker = googleMap.addMarker(MarkerOptions().position(latLng).title(locationAddress))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        }
    }
}