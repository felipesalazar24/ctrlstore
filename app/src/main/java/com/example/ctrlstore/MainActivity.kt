package com.example.ctrlstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.ctrlstore.navegation.AppRoutes
import com.example.ctrlstore.ui.screens.auth.LoginScreen
import com.example.ctrlstore.ui.screens.auth.RegisterScreen
import com.example.ctrlstore.ui.screens.cart.CartScreen
import com.example.ctrlstore.ui.screens.home.HomeScreen
import com.example.ctrlstore.ui.screens.products.ProductDetailScreen
import com.example.ctrlstore.ui.screens.products.ProductsScreen
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
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf(AppRoutes.Login.route) }
    var selectedProductId by remember { mutableStateOf(0) }

    when (currentScreen) {
        AppRoutes.Login.route -> LoginScreen(
            onLoginSuccess = {
                currentScreen = AppRoutes.Home.route
            },
            onNavigateToRegister = {
                currentScreen = AppRoutes.Register.route
            }
        )
        AppRoutes.Register.route -> RegisterScreen(
            onRegisterSuccess = {
                currentScreen = AppRoutes.Home.route
            },
            onNavigateToLogin = {
                currentScreen = AppRoutes.Login.route
            }
        )
        AppRoutes.Home.route -> HomeScreen(
            onNavigateToProducts = {
                currentScreen = AppRoutes.Products.route
            },
            onNavigateToCart = {
                currentScreen = AppRoutes.Cart.route
            }
        )
        AppRoutes.Products.route -> ProductsScreen(
            onNavigateToHome = {
                currentScreen = AppRoutes.Home.route
            },
            onNavigateToCart = {
                currentScreen = AppRoutes.Cart.route
            },
            onNavigateToProductDetail = { productId ->
                selectedProductId = productId
                currentScreen = "product_detail"
            }
        )
        AppRoutes.Cart.route -> CartScreen(
            onNavigateToHome = {
                currentScreen = AppRoutes.Home.route
            },
            onNavigateToProducts = {
                currentScreen = AppRoutes.Products.route
            }
        )
        "product_detail" -> ProductDetailScreen(
            productId = selectedProductId,
            onNavigateBack = {
                currentScreen = AppRoutes.Products.route
            },
            onNavigateToCart = {
                currentScreen = AppRoutes.Cart.route
            }
        )
    }
}