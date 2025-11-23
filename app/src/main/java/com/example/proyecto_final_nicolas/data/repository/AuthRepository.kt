package com.example.proyecto_final_nicolas.data.repository

import com.example.proyecto_final_nicolas.data.model.ChangePasswordRequest
import com.example.proyecto_final_nicolas.data.model.LoginResponse
import com.example.proyecto_final_nicolas.data.model.User
import com.example.proyecto_final_nicolas.data.net.ApiClient

class AuthRepository(private val apiClient: ApiClient) {

    suspend fun login(user: User): LoginResponse {
        return apiClient.login(user)
    }

    suspend fun register(user: User) {
        apiClient.register(user)
    }

    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest) {
        apiClient.changePassword(changePasswordRequest)
    }
}