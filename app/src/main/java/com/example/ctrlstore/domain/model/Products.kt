package com.example.ctrlstore.domain.model

import com.google.gson.annotations.SerializedName
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("precio") val precio: Double,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("categoria") val atributo: String // Mapea "categoria" del JSON a tu variable "atributo"
) {
    // --- LÓGICA DE GITHUB (TU VERSIÓN EXACTA) ---
    val imagenUrl: String
        get() {
            val baseImageUrl = "https://raw.githubusercontent.com/felipesalazar24/ctrlstore-images/main"
            // Codificar espacios y símbolos
            val nombreArchivo = URLEncoder.encode(nombre, StandardCharsets.UTF_8.toString()).replace("+", "%20")
            // Construir URL: .../products/Mouse/Logitech%20G502(1).jpg
            return "$baseImageUrl/products/$atributo/$nombreArchivo(1).jpg"
        }
}