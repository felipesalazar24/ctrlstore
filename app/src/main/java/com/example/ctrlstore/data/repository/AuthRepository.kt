package com.example.ctrlstore.data.repository

import com.example.ctrlstore.domain.model.LoginResponse
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
}