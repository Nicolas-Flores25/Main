package com.example.proyecto_final_nicolas.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_final_nicolas.data.model.ChangePasswordRequest
import com.example.proyecto_final_nicolas.databinding.ActivityProfileBinding
import com.example.proyecto_final_nicolas.ui.ViewModelFactory
import com.example.proyecto_final_nicolas.ui.auth.AuthViewModel
import com.example.proyecto_final_nicolas.ui.auth.LoginActivity
import com.example.proyecto_final_nicolas.ui.destinations.DestinationsActivity
import com.example.proyecto_final_nicolas.util.Result

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel: AuthViewModel by viewModels { ViewModelFactory() }
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra("USERNAME") ?: ""

        if (username.isEmpty()) {
            Toast.makeText(this, "Error: No se pudo obtener el usuario", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        binding.btnTakePhoto.setOnClickListener {

        }

        binding.btnSelectPhoto.setOnClickListener {

        }

        binding.btnGoToDestinations.setOnClickListener {
            startActivity(Intent(this, DestinationsActivity::class.java))
        }

        binding.btnChangePassword.setOnClickListener {
            val oldPassword = binding.etOldPassword.text.toString().trim()
            val newPassword = binding.etNewPassword.text.toString().trim()

            if (oldPassword.isNotEmpty() && newPassword.isNotEmpty()) {
                viewModel.changePassword(ChangePasswordRequest(username, oldPassword, newPassword))
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.changePasswordResult.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    Toast.makeText(this, "Contraseña cambiada con éxito", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                is Result.Error -> {
                    Toast.makeText(this, "Error al cambiar la contraseña: ${result.exception.message}", Toast.LENGTH_LONG).show()
                }
                is Result.Loading -> {

                }
            }
        }
    }
}