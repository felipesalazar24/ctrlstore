package com.example.ctrlstore.data.repository

import com.example.ctrlstore.domain.model.LoginResponse
import com.example.ctrlstore.domain.model.RegisterResponse
import com.example.ctrlstore.domain.model.RegisterRequest
import com.example.ctrlstore.domain.model.User
import kotlinx.coroutines.delay

class AuthRepository {

    suspend fun login(email: String, password: String): LoginResponse {
        delay(1000)

        return try {
            when {
                email == "fe.salazarv@duocuc.cl" && password == "adminfelipe" -> {
                    LoginResponse(
                        success = true,
                        user = User("1", email, "Felipe", "admin"),
                        isAdmin = true
                    )
                }
                email == "mati.vegaa@duocuc.cl" && password == "adminmatias" -> {
                    LoginResponse(
                        success = true,
                        user = User("2", email, "Matias", "admin"),
                        isAdmin = true
                    )
                }
                email == "aa.lorca@duocuc.cl" && password == "adminaron" -> {
                    LoginResponse(
                        success = true,
                        user = User("3", email, "Aron", "admin"),
                        isAdmin = true
                    )
                }
                email.isNotBlank() && password.isNotBlank() -> {
                    LoginResponse(
                        success = false,
                        message = "Correo o contraseña incorrecto"
                    )
                }
                else -> {
                    LoginResponse(
                        success = false,
                        message = "Correo o contraseña incorrecto"
                    )
                }
            }
        } catch (e: Exception) {
            LoginResponse(
                success = false,
                message = "Error de red: ${e.message}"
            )
        }
    }
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
            RegisterResponse(
                success = true,
                user = User(
                    id = System.currentTimeMillis().toString(),
                    email = registerRequest.email,
                    nombre = registerRequest.nombre,
                    rol = "user"
                )
            )

        } catch (e: Exception) {
            RegisterResponse(
                success = false,
                message = "Error de red: ${e.message}"
            )
        }
    }
}
