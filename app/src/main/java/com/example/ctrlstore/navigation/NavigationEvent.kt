package com.example.ctrlstore.navegation

sealed class NavigationEvent {
    data class NavigateTo(val route: String) : NavigationEvent()
    object NavigateBack : NavigationEvent()
    data class NavigateToWithData(val route: String, val data: Map<String, String> = emptyMap()) : NavigationEvent()
}