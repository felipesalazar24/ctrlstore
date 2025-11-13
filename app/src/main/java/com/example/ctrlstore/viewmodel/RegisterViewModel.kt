package com.example.ctrlstore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctrlstore.domain.model.RegisterRequest
import com.example.ctrlstore.domain.model.User
import com.example.ctrlstore.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val registerUseCase = RegisterUseCase(
        com.example.ctrlstore.data.repository.AuthRepository()
    )

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    private val _formErrors = MutableStateFlow<RegisterFormErrors>(RegisterFormErrors())
    val formErrors: StateFlow<RegisterFormErrors> = _formErrors

    fun register(nombre: String, email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            val errors = validateForm(nombre, email, password, confirmPassword)
            if (errors.hasErrors()) {
                _formErrors.value = errors
                return@launch
            }

            _registerState.value = RegisterState.Loading
            _formErrors.value = RegisterFormErrors()

            val result = registerUseCase(
                RegisterRequest(
                    nombre = nombre,
                    email = email,
                    password = password
                )
            )

            if (result.success) {
                _registerState.value = RegisterState.Success(result.user!!)
            } else {
                _registerState.value = RegisterState.Error(result.message ?: "Error en el registro")
            }
        }
    }

    private fun validateForm(
        nombre: String,
        email: String,
        password: String,
        confirmPassword: String
    ): RegisterFormErrors {
        return RegisterFormErrors(
            nombre = if (nombre.isBlank()) "El nombre es obligatorio" else null,
            email = when {
                email.isBlank() -> "El email es obligatorio"
                !email.contains("@") -> "Formato de email inválido"
                else -> null
            },
            password = when {
                password.isBlank() -> "La contraseña es obligatoria"
                password.length < 6 -> "Mínimo 6 caracteres"
                else -> null
            },
            confirmPassword = when {
                confirmPassword.isBlank() -> "Confirma tu contraseña"
                password != confirmPassword -> "Las contraseñas no coinciden"
                else -> null
            }
        )
    }

    fun clearErrors() {
        _formErrors.value = RegisterFormErrors()
    }
}
sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val user: User) : RegisterState()
    data class Error(val message: String) : RegisterState()
}
data class RegisterFormErrors(
    val nombre: String? = null,
    val email: String? = null,
    val password: String? = null,
    val confirmPassword: String? = null,
    val general: String? = null
) {
    fun hasErrors(): Boolean {
        return nombre != null || email != null || password != null ||
                confirmPassword != null || general != null
    }
}