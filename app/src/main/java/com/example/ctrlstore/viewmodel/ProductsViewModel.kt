package com.example.ctrlstore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctrlstore.data.repository.ProductRepository
import com.example.ctrlstore.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    private val _productsState = MutableStateFlow<ProductsState>(ProductsState.Loading)
    val productsState: StateFlow<ProductsState> = _productsState

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _productsState.value = ProductsState.Loading
            try {
                val products = productRepository.getAllProducts()
                _productsState.value = ProductsState.Success(products)
            } catch (e: Exception) {
                _productsState.value = ProductsState.Error("Error al cargar productos: ${e.message}")
            }
        }
    }

    fun getProductsByCategory(category: String): List<Product> {
        return when (val state = _productsState.value) {
            is ProductsState.Success -> state.products.filter {
                it.atributo.equals(category, ignoreCase = true)
            }
            else -> emptyList()
        }
    }

    fun setSelectedProduct(product: Product) {
        _selectedProduct.value = product
    }

    fun clearSelectedProduct() {
        _selectedProduct.value = null
    }
}

sealed class ProductsState {
    object Loading : ProductsState()
    data class Success(val products: List<Product>) : ProductsState()
    data class Error(val message: String) : ProductsState()
}