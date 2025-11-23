package com.example.proyecto_final_nicolas.data.net

import com.example.proyecto_final_nicolas.data.model.ChangePasswordRequest
import com.example.proyecto_final_nicolas.data.model.LoginResponse
import com.example.proyecto_final_nicolas.data.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body user: User): LoginResponse

    @POST("register")
    suspend fun register(@Body user: User)

    @POST("change-password")
    suspend fun changePassword(@Body changePasswordRequest: ChangePasswordRequest)
}