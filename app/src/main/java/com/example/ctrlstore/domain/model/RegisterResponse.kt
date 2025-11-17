package com.example.ctrlstore.domain.model

data class RegisterResponse (
    val success: Boolean,
    val user: User? = null,
    val message: String? = null
)