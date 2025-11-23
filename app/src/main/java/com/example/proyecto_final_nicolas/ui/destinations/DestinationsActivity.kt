package com.example.proyecto_final_nicolas.ui.destinations

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_final_nicolas.R
import com.example.proyecto_final_nicolas.data.model.Destination
import com.example.proyecto_final_nicolas.databinding.ActivityDestinationsBinding

class DestinationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.btnBackToProfile.setOnClickListener {
            finish() // Cierra la actividad y vuelve a la anterior (ProfileActivity)
        }
    }

    private fun setupRecyclerView() {
        val destinations = listOf(
            Destination("Parque Nacional Tikal", "Ruinas mayas en medio de la selva.", R.mipmap.ic_launcher, 17.2220, -89.6236),
            Destination("Lago de Atitlán", "Uno de los lagos más bellos del mundo.", R.mipmap.ic_launcher, 14.6948, -91.2023),
            Destination("Antigua Guatemala", "Ciudad colonial con calles empedradas.", R.mipmap.ic_launcher, 14.5573, -90.7332),
            Destination("Semuc Champey", "Pozas de agua turquesa en medio del bosque.", R.mipmap.ic_launcher, 15.5350, -89.9583),
            Destination("Chichicastenango", "Mercado de artesanías y textiles.", R.mipmap.ic_launcher, 14.9425, -91.1111)

        )

        val adapter = DestinationsAdapter(destinations) { destination ->
            val intent = Intent(this, DestinationDetailActivity::class.java).apply {
                putExtra("EXTRA_DESTINATION", destination)
            }
            startActivity(intent)
        }
        binding.rvDestinations.adapter = adapter
        binding.rvDestinations.layoutManager = LinearLayoutManager(this)
    }
}