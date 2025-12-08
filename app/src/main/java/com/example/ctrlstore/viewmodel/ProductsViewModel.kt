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

    private val localRepo = ProductRepository()

    private val _productos = MutableStateFlow<List<Product>>(emptyList())
    val productos: StateFlow<List<Product>> = _productos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun cargarProductos() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = ApiClient.inventarioApi.getProductos()

                val remoteList: List<RemoteProduct> =
                    if (response.isSuccessful && response.body() != null) {
                        response.body()!!
                    } else {
                        emptyList()
                    }

                val staticList = localRepo.getAllProducts()

                val finalList: List<Product> =
                    if (remoteList.isNotEmpty()) {
                        remoteList.map { remote ->
                            val template = staticList.find { it.id == remote.id }

                            Product(
                                id = remote.id,
                                nombre = remote.nombre,
                                precio = remote.precio,
                                descripcion = remote.descripcion,
                                imagen = template?.imagen ?: "",
                                miniaturas = template?.miniaturas ?: emptyList(),
                                atributo = remote.categoria
                            )
                        }
                    } else {
                        staticList
                    }

                _productos.value = finalList

            } catch (e: Exception) {
                _error.value = e.message ?: "Error al cargar productos"
                _productos.value = localRepo.getAllProducts() // fallback
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun cargarProductosPorCategoria(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val lista = productos.value.filter {
                    it.atributo.equals(category, ignoreCase = true)
                }
                _productos.value = lista
            } catch (e: Exception) {
                _error.value = e.message ?: "Error al filtrar productos"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
