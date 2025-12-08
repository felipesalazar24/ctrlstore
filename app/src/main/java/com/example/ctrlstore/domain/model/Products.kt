package com.example.ctrlstore.domain.model

data class Product(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val imagen: String,
    val descripcion: String,
    val miniaturas: List<String>,
    val atributo: String
){
    val precioFormateado: String
        get() = "$${precio.toString().replace(Regex("(\\d)(?=(\\d{3})+(?!\\d))"), "$1.")}"

    val category: String
        get() = when (atributo.toLowerCase()) {
            "mouse" -> "Mouse"
            "teclado" -> "Teclado"
            "audifono" -> "Audífono"
            "monitor" -> "Monitor"
            else -> atributo
        }
}