package com.example.ctrlstore.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ctrlstore.data.repository.ProductRepository
import com.example.ctrlstore.domain.model.Product

class ProductsViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    fun getAllProducts(): List<Product> {
        return try {
            productRepository.getAllProducts()
        } catch (e: Exception) {
            // Retorna lista vacía en caso de error
            emptyList()
        }
    }

    fun getProductsByCategory(category: String): List<Product> {
        return productRepository.getProductsByCategory(category)
    }

    fun getCategories(): List<String> {
        return getAllProducts().map { it.atributo }.distinct()
    }
}