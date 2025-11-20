package com.example.ctrlstore.domain.model

data class RegisterRequest(
    val nombre: String,
    val email: String,
    val password: String,
    val telefono: String? = null,
    val direccion: String? = null
)
