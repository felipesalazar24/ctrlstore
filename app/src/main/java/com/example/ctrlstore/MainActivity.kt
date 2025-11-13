package com.example.ctrlstore

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

                    when (currentScreen) {
                        "login" -> LoginScreen(
                            onLoginSuccess = {
                                currentScreen = "home"
                            },
                            onNavigateToRegister = {
                                currentScreen = "home"
                            }
                        )
                        "home" -> HomeScreen()
                    }
                }
            }
        }
    }
}
//home temporal
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "¡Bienvenido al Home!",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}