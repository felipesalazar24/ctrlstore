package com.example.ctrlstore.domain.model

data class LoginResponse (
    val success: Boolean,
    val user: User? = null,
    val message: String? = null,
    val isAdmin: Boolean = false
)