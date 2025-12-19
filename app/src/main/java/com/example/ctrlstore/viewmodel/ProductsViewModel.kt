package com.example.ctrlstore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctrlstore.data.repository.ProductRepository
import com.example.ctrlstore.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    private val repository = ProductRepository()

    // Estado de la lista
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    // Estado de carga (para mostrar un spinner si quieres)
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            // Llamada al repositorio
            val result = repository.getAllProducts()
            _products.value = result
            _isLoading.value = false
        }
    }
}