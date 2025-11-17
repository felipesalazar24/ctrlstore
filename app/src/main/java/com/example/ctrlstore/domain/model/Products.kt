package com.example.ctrlstore.domain.model

data class Product(
    val id: Int,
    val nombre: String,
    val precio: Int,
    val imagen: String,
    val descripcion: String,
    val miniaturas: List<String>,
    val atributo: String
)