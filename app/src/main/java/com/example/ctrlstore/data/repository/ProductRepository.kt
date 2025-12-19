package com.example.ctrlstore.data.repository

import android.util.Log
import com.example.ctrlstore.data.remote.ApiClient
import  com.example.ctrlstore.domain.model.Product

class ProductRepository {
    private val api = ApiClient.inventarioApi

    suspend fun getAllProducts(): List<Product> {
        return try {
            val response = api.obtenerProductos()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                Log.e("Repo", "Error API: ${response.code()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("Repo", "Error Conexión: ${e.message}")
            emptyList()
        }
    }

    suspend fun getProductsByCategory(categoria: String): List<Product> {
        val todos = getAllProducts()
        return todos.filter { it.atributo.equals(categoria, ignoreCase = true) }
    }
}