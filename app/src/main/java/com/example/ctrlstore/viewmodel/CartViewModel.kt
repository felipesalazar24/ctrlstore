package com.example.ctrlstore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctrlstore.data.local.CartItem
import com.example.ctrlstore.data.local.CartStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val _cart = MutableStateFlow<List<CartItem>>(emptyList())
    val cart: StateFlow<List<CartItem>> = _cart

    init {
        loadCart()
    }

    private fun loadCart() {
        _cart.value = CartStorage.getCart(getApplication())
    }

    fun addItem(item: CartItem) {
        viewModelScope.launch {
            CartStorage.addItem(getApplication(), item)
            _cart.value = CartStorage.getCart(getApplication())
        }
    }

    fun updateItem(item: CartItem) {
        viewModelScope.launch {
            CartStorage.updateItem(getApplication(), item)
            _cart.value = CartStorage.getCart(getApplication())
        }
    }

    fun removeItem(productId: String) {
        viewModelScope.launch {
            CartStorage.removeItem(getApplication(), productId)
            _cart.value = CartStorage.getCart(getApplication())
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            CartStorage.clearCart(getApplication())
            _cart.value = emptyList()
        }
    }
}