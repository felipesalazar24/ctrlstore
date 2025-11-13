package com.example.ctrlstore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctrlstore.domain.model.User
import com.example.ctrlstore.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val _formErrors = MutableStateFlow<FormErrors>(FormErrors())
    val formErrors: StateFlow<FormErrors> = _formErrors

    fun login(email: String, password: String) {
        viewModelScope.launch {
            if (email.isBlank()) {
                _formErrors.value = FormErrors(email = "Por favor ingresa tu correo electrónico.")
                return@launch
            }

            if (password.isBlank()) {
                _formErrors.value = FormErrors(password = "Por favor ingresa tu contraseña.")
                return@launch
            }

            _loginState.value = LoginState.Loading
            _formErrors.value = FormErrors()

            val result = loginUseCase(email, password)

            if (result.success) {
                _loginState.value = LoginState.Success(result.user!!, result.isAdmin)
            } else {
                _loginState.value = LoginState.Error(result.message ?: "Error desconocido")
            }
        }
    }

    fun clearErrors() {
        _formErrors.value = FormErrors()
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: User, val isAdmin: Boolean) : LoginState()
    data class Error(val message: String) : LoginState()
}

data class FormErrors(
    val email: String? = null,
    val password: String? = null,
    val general: String? = null
)