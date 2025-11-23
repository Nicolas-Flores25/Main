package com.example.proyecto_final_nicolas.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_final_nicolas.data.net.ApiClient
import com.example.proyecto_final_nicolas.data.net.RetrofitClient
import com.example.proyecto_final_nicolas.data.repository.AuthRepository
import com.example.proyecto_final_nicolas.ui.auth.AuthViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            val apiClient = ApiClient(RetrofitClient.instance)
            val authRepository = AuthRepository(apiClient)
            return AuthViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}