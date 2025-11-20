package com.example.ctrlstore.data.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class CartItem(
    val productId: String,
    val title: String,
    val price: Double,
    val imageUrl: String? = null,
    var quantity: Int = 1
)

object CartStorage {
    private const val PREFS_NAME = "ctrlstore_prefs"
    private const val KEY_CART = "cart"
    private val gson = Gson()

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getCart(context: Context): MutableList<CartItem> {
        val json = prefs(context).getString(KEY_CART, null) ?: return mutableListOf()
        val type = object : TypeToken<List<CartItem>>() {}.type
        return try {
            gson.fromJson<List<CartItem>>(json, type).toMutableList()
        } catch (e: Exception) {
            mutableListOf()
        }
    }

    fun saveCart(context: Context, cart: List<CartItem>) {
        val json = gson.toJson(cart)
        prefs(context).edit().putString(KEY_CART, json).apply()
    }

    fun addItem(context: Context, item: CartItem) {
        val cart = getCart(context)
        val idx = cart.indexOfFirst { it.productId == item.productId }
        if (idx >= 0) {
            cart[idx].quantity = cart[idx].quantity + item.quantity
        } else {
            cart.add(item)
        }
        saveCart(context, cart)
    }

    fun updateItem(context: Context, item: CartItem) {
        val cart = getCart(context)
        val idx = cart.indexOfFirst { it.productId == item.productId }
        if (idx >= 0) {
            cart[idx] = item
            saveCart(context, cart)
        }
    }

    fun removeItem(context: Context, productId: String) {
        val newCart = getCart(context).filterNot { it.productId == productId }
        saveCart(context, newCart)
    }

    fun clearCart(context: Context) {
        saveCart(context, emptyList())
    }
}

