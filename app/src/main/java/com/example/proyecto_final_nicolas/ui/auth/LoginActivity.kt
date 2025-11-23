package com.example.proyecto_final_nicolas.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_final_nicolas.data.model.User
import com.example.proyecto_final_nicolas.databinding.ActivityLoginBinding
import com.example.proyecto_final_nicolas.ui.ViewModelFactory
import com.example.proyecto_final_nicolas.ui.profile.ProfileActivity
import com.example.proyecto_final_nicolas.util.Result

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            val username = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(User(user = username, password = password))
            } else {
                Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginResult.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    Toast.makeText(this, "Login iniciado con exito...", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ProfileActivity::class.java).apply {
                        putExtra("USERNAME", binding.username.text.toString().trim())
                    }
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                is Result.Error -> {
                    Toast.makeText(this, "Ha ocurrido un error en el login: ${result.exception.message}", Toast.LENGTH_LONG).show()
                }
                is Result.Loading -> {
                    // hacer una progres bar
                }
            }
        }
    }
}