package com.example.ctrlstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ctrlstore.data.local.CartItem
import com.example.ctrlstore.data.local.CartStorage
import com.example.ctrlstore.data.local.UserStorage
import com.example.ctrlstore.ui.screens.auth.LoginScreen
import com.example.ctrlstore.ui.screens.auth.RegisterScreen
import com.example.ctrlstore.ui.screens.products.ProductDetailScreen
import com.example.ctrlstore.ui.screens.products.ProductsScreen
import com.example.ctrlstore.ui.screens.cart.CartScreen
import com.example.ctrlstore.ui.screens.home.HomeScreen
import com.example.ctrlstore.ui.theme.CTRLstoreTheme
import com.example.ctrlstore.viewmodel.CartViewModel
import com.example.ctrlstore.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initialScreen = if (UserStorage.isUserLoggedIn(this)) {
            "home"
        } else {
            "login"
        }

        setContent {
            CTRLstoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val cartViewModel: CartViewModel = viewModel()
                    val loginViewModel: LoginViewModel = viewModel()

                    var currentScreen by remember { mutableStateOf(initialScreen) }
                    var selectedProductId by remember { mutableIntStateOf(0) }

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
                            onNavigateToProducts = { currentScreen = "products" },
                            onNavigateToCart = { currentScreen = "cart" },
                            onLogout = {
                                try {
                                    UserStorage.setLoggedOut(this@MainActivity, true)
                                    loginViewModel.resetState()
                                } catch (e: Exception) {
                                    println("SESION: Error al hacer logout: ${e.message}")
                                }
                                currentScreen = "login"
                            }
                        )
                        "products" -> ProductsScreen(
                            onBackClick = {
                                currentScreen = "home"
                            },
                            onProductClick = { product ->
                                selectedProductId = product.id
                                currentScreen = "productDetail"
                            },
                            onNavigateToCart = {
                                currentScreen = "cart"
                            }
                        )
                        "productDetail" -> ProductDetailScreen(
                            productId = selectedProductId,
                            onBackClick = {
                                currentScreen = "products"
                            },
                            onAddToCart = { product ->
                                val item = CartItem(
                                    productId = product.id.toString(),
                                    title = product.nombre,
                                    price = product.precio.toDouble(),
                                    imageUrl = product.imagen,
                                    quantity = 1
                                )
                                cartViewModel.addItem(item)
                            },
                            onNavigateToCart = {
                                currentScreen = "cart"
                            }
                        )
                        "cart" -> CartScreen(
                            onNavigateToProducts = {
                                currentScreen = "products"
                            },
                            onNavigateToHome = {
                                currentScreen = "home"
                            }
                        )
                    }
                }
            }
        }
    }
}