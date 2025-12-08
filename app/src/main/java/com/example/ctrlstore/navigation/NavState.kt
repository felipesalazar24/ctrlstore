package com.example.ctrlstore.navegation

data class NavState(
    val route: String = AppRoutes.Home.route,
    val category: String? = null,
    val productId: Int? = null
)