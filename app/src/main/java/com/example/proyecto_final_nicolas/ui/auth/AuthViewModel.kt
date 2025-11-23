package com.example.proyecto_final_nicolas.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_final_nicolas.data.model.ChangePasswordRequest
import com.example.proyecto_final_nicolas.data.model.LoginResponse
import com.example.proyecto_final_nicolas.data.model.User
import com.example.proyecto_final_nicolas.data.repository.AuthRepository
import com.example.proyecto_final_nicolas.util.Result
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> = _loginResult

    private val _registerResult = MutableLiveData<Result<Unit>>()
    val registerResult: LiveData<Result<Unit>> = _registerResult

    private val _changePasswordResult = MutableLiveData<Result<Unit>>()
    val changePasswordResult: LiveData<Result<Unit>> = _changePasswordResult

    fun login(user: User) {
        viewModelScope.launch {
            _loginResult.value = Result.Loading
            try {
                val response = authRepository.login(user)
                _loginResult.value = Result.Success(response)
            } catch (e: Exception) {
                _loginResult.value = Result.Error(e)
            }
        }
    }

    fun register(user: User) {
        viewModelScope.launch {
            _registerResult.value = Result.Loading
            try {
                authRepository.register(user)
                _registerResult.value = Result.Success(Unit)
            } catch (e: Exception) {
                _registerResult.value = Result.Error(e)
            }
        }
    }

    fun changePassword(changePasswordRequest: ChangePasswordRequest) {
        viewModelScope.launch {
            _changePasswordResult.value = Result.Loading
            try {
                authRepository.changePassword(changePasswordRequest)
                _changePasswordResult.value = Result.Success(Unit)
            } catch (e: Exception) {
                _changePasswordResult.value = Result.Error(e)
            }
        }
    }
}