package com.example.ctrlstore.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctrlstore.data.local.StoredUser
import com.example.ctrlstore.data.local.UserStorage
import com.example.ctrlstore.data.remote.ApiClient
import com.example.ctrlstore.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioApi = ApiClient.usuarioApi

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

            try {
                val usuarios: List<User> = usuarioApi.getUsuarios()

                val userDomain = usuarios.firstOrNull { user ->
                    user.email == email && user.password == password
                }

                if (userDomain != null) {
                    val isAdmin = userDomain.rol.equals("ADMIN", ignoreCase = true)

                    val stored = StoredUser(
                        id = userDomain.id,
                        name = userDomain.nombre,
                        email = userDomain.email,
                        password = password,
                        token = null
                    )

                    if (!isAdmin) {
                        UserStorage.saveUser(getApplication(), stored)
                        UserStorage.setLoggedOut(getApplication(), false)
                    }

                    _loginState.value = LoginState.Success(userDomain, isAdmin)
                } else {
                    val local = UserStorage.getUser(getApplication())
                    if (local != null && local.email == email && local.password == password) {
                        val userDomainOffline = User(
                            id = local.id,
                            email = local.email ?: email,
                            nombre = local.name ?: "",
                            rol = "user",
                            password= local.password ?: password
                        )
                        UserStorage.setLoggedOut(getApplication(), false)
                        _loginState.value = LoginState.Success(userDomainOffline, false)
                    } else {
                        _loginState.value = LoginState.Error("Correo o contraseña incorrectos.")
                    }
                }

            } catch (e: Exception) {
                Log.e("LOGIN", "Error al conectar con el backend", e)
                _loginState.value =
                    LoginState.Error("No se pudo conectar con el servidor. Inténtalo nuevamente.")
            }
        }
    }


    fun testBackendConnection() {
        viewModelScope.launch {
            try {
                val usuarios = usuarioApi.getUsuarios()
                Log.d("API_TEST", "Usuarios recibidos desde backend: ${usuarios.size}")
            } catch (e: Exception) {
                Log.e("API_TEST", "Error al probar conexión con backend", e)
            }
        }
    }

    fun resetState() {
        _loginState.value = LoginState.Idle
        _formErrors.value = FormErrors()
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
