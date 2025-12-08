package com.example.ctrlstore.domain.model

data class RegisterRequest(
    val nombre: String,
    val email: String,
    val password: String
)
