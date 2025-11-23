package com.example.proyecto_final_nicolas.ui.destinations

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_final_nicolas.data.model.Destination
import com.example.proyecto_final_nicolas.databinding.ActivityDestinationDetailBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DestinationDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityDestinationDetailBinding
    private var destination: Destination? = null
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        destination = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_DESTINATION", Destination::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_DESTINATION")
        }

        destination?.let {
            binding.ivDetailImage.setImageResource(it.imageResId)
            binding.tvDetailTitle.text = it.title
            binding.tvDetailDescription.text = it.description
        }
        binding.buttonRating.setOnClickListener {
            binding.ratingText.text = "Tu puntaje : ${binding.ratingBar.rating}"
        }
        val stars = binding.ratingBar.progressDrawable as LayerDrawable
        stars.getDrawable(2).setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)

        binding.btnBackToDestinations.setOnClickListener {
            finish()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        destination?.let {
            val location = LatLng(it.latitude, it.longitude)
            googleMap?.addMarker(MarkerOptions().position(location).title(it.title))
            googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }
}