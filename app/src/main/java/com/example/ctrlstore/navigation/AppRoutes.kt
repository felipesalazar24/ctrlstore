package com.example.ctrlstore.navegation
sealed class AppRoutes(val route: String) {
    object Login : AppRoutes("login")
    object Register : AppRoutes("register")
    object Home : AppRoutes("home")
    object Products : AppRoutes("products")
    object Cart : AppRoutes("cart")
    object Profile : AppRoutes("profile")

    companion object {
        fun getRouteForName(name: String): String {
            return when (name) {
                "Login" -> Login.route
                "Register" -> Register.route
                "Home" -> Home.route
                "Products" -> Products.route
                "Cart" -> Cart.route
                "Profile" -> Profile.route
                else -> Home.route
            }
        }
    }
}