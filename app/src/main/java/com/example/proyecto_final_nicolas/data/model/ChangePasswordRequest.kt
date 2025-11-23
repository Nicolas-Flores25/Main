package com.example.proyecto_final_nicolas.data.model

data class ChangePasswordRequest(val user: String, val oldPassword: String, val newPassword: String)