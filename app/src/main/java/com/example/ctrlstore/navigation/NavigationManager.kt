package com.example.ctrlstore.navegation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationManager {

    private val _navState = MutableStateFlow(NavState())
    val  NavState: StateFlow<NavState> = _navState

    fun navigateTo (route: String){
        _navState.value = NavState(route = route)
    }

    fun navigateToProductsByCategory(category: String) {
        _navState.value = NavState(
            route = AppRoutes.ProductsByCategory.route, // "productsByCategory/{category}"
            category = category
        )
    }

    fun navigateBackToHome() {
        _navState.value = NavState(route = AppRoutes.Home.route)
    }
    @Composable
    fun rememberNavigationManager(): MutableState<String> {
        return remember { mutableStateOf(AppRoutes.Home.route) }
    }
}