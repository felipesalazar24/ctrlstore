package com.example.ctrlstore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ctrlstore.ui.screens.auth.LoginScreen
import com.example.ctrlstore.ui.screens.auth.RegisterScreen
import com.example.ctrlstore.ui.screens.products.ProductDetailScreen
import com.example.ctrlstore.ui.screens.products.ProductsScreen
import com.example.ctrlstore.ui.theme.screen.HomeScreen
import com.example.ctrlstore.ui.theme.CTRLstoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CTRLstoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf("login") }
                    var selectedProductId by remember { mutableStateOf(0) }

                    when (currentScreen) {
                        "login" -> LoginScreen(
                            onLoginSuccess = {
                                currentScreen = "home"
                            },
                            onNavigateToRegister = {
                                currentScreen = "register"
                            }
                        )
                        "register" -> RegisterScreen(
                            onRegisterSuccess = {
                                currentScreen = "home"
                            },
                            onNavigateToLogin = {
                                currentScreen = "login"
                            }
                        )
                        "home" -> HomeScreen(
                            onNavigateToProducts = {
                                currentScreen = "products"
                            },
                            onNavigateToCart = {
                                println("Carrito - En desarrollo")
                            },
                            onLogout = {
                                clearAllSessionData()
                                recreateActivity()
                            }
                        ) // QUITAMOS onProductClick temporalmente
                        "products" -> ProductsScreen(
                            onBackClick = {
                                currentScreen = "home"
                            },
                            onProductClick = { product ->
                                selectedProductId = product.id
                                currentScreen = "productDetail"
                            }
                        )
                        "productDetail" -> ProductDetailScreen(
                            productId = selectedProductId,
                            onBackClick = {
                                currentScreen = "products"
                            },
                            onAddToCart = { product ->
                                println("Producto agregado al carrito: ${product.nombre}")
                            }
                        )
                    }
                }
            }
        }
    }

    private fun clearAllSessionData() {
        try {
            val sharedPref = getSharedPreferences("app_session", Context.MODE_PRIVATE)
            sharedPref.edit().clear().apply()
            println("SESION: Todos los datos de sesión limpiados")
        } catch (e: Exception) {
            println("SESION: Error limpiando datos: ${e.message}")
        }
    }

    private fun recreateActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}

@Composable
fun SimpleHomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Pantalla Home Simple")
    }
}