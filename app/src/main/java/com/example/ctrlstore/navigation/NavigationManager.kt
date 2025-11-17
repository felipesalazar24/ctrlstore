package com.example.ctrlstore.navegation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun rememberNavigationManager(): MutableState<String> {
    return remember { mutableStateOf(AppRoutes.Home.route) }
}