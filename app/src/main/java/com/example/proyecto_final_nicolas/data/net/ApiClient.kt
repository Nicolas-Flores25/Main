package com.example.proyecto_final_nicolas.data.net

import com.example.proyecto_final_nicolas.data.model.ChangePasswordRequest
import com.example.proyecto_final_nicolas.data.model.LoginResponse
import com.example.proyecto_final_nicolas.data.model.User

class ApiClient(private val apiService: ApiService) {

    suspend fun login(user: User): LoginResponse {
        return apiService.login(user)
    }

    suspend fun register(user: User) {
        apiService.register(user)
    }

    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest) {
        apiService.changePassword(changePasswordRequest)
    }
}