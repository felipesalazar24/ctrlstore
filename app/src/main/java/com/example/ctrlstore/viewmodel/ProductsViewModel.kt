package com.example.ctrlstore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctrlstore.data.remote.ApiClient
import com.example.ctrlstore.data.repository.ProductRepository
import com.example.ctrlstore.domain.model.Product
import com.example.ctrlstore.domain.model.RemoteProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _productos = MutableStateFlow<List<Product>>(emptyList())
    val productos: StateFlow<List<Product>> = _productos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun cargarProductos() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                _productos.value = repository.getAllProducts()
            } catch (e: Exception) {
                _error.value = e.message ?: "Error al cargar productos"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun cargarProductosPorCategoria(category: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                _productos.value = repository.getProductsByCategory(category)
            } catch (e: Exception) {
                _error.value = e.message ?: "Error al filtrar productos"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
