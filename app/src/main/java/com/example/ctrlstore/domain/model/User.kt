package com.example.ctrlstore.domain.model

data class User (
    val id: Int? = null,
    val email: String,
    val nombre:String,
    val rol: String,
    val password: String
)