package com.example.ctrlstore.data.repository

import  com.example.ctrlstore.data.remote.ApiClient.usuarioApi
import com.example.ctrlstore.domain.model.RegisterResponse
import com.example.ctrlstore.domain.model.RegisterRequest
import com.example.ctrlstore.domain.model.User
import kotlinx.coroutines.delay

class AuthRepository {
    suspend fun register(registerRequest: RegisterRequest): RegisterResponse {
        delay(1000)

        return try {
            if (registerRequest.nombre.isBlank() ||
                registerRequest.email.isBlank() ||
                registerRequest.password.isBlank()) {
                return RegisterResponse(
                    success = false,
                    message = "Todos los campos son obligatorios"
                )
            }
            if (!registerRequest.email.contains("@")) {
                return RegisterResponse(
                    success = false,
                    message = "El formato del email no es válido"
                )
            }
            if (registerRequest.password.length < 6) {
                return RegisterResponse(
                    success = false,
                    message = "La contraseña debe tener al menos 6 caracteres"
                )
            }

            val userToCreate = User(
                id= null,
                email = registerRequest.email,
                nombre = registerRequest.nombre,
                rol = "cliente",
                password = registerRequest.password
            )

            val createdUser = usuarioApi.createUsuario(userToCreate)

            RegisterResponse(
                success = true,
                user = createdUser,
                message = null
            )

        } catch (e: Exception) {
            RegisterResponse(
                success = false,
                message = "Error al registrar usuario: ${e.message}"
            )
        }
    }
}
