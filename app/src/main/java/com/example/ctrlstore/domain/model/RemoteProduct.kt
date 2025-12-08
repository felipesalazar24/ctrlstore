package com.example.ctrlstore.domain.model

data class RemoteProduct (
    val id: Int,
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val categoria: String
)