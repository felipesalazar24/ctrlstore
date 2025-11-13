package com.example.ctrlstore.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ctrlstore.viewmodel.LoginViewModel
import com.example.ctrlstore.viewmodel.LoginState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val loginState by viewModel.loginState.collectAsState()
    val formErrors by viewModel.formErrors.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Manejar estado de éxito
    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Iniciar Sesión",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 32.dp)
        )

        Text(
            text = "Ingresa a tu cuenta",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Error general
        if (loginState is LoginState.Error) {
            val errorState = loginState as LoginState.Error
            Text(
                text = errorState.message,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Email
        Text(
            text = "Correo Electrónico",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Start
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                viewModel.clearErrors()
            },
            placeholder = { Text("tu@email.com") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            isError = formErrors.email != null
        )

        if (formErrors.email != null) {
            Text(
                text = formErrors.email!!,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )
        }

        // Password
        Text(
            text = "Contraseña",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Start
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                viewModel.clearErrors()
            },
            placeholder = { Text("Tu contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            isError = formErrors.password != null
        )

        if (formErrors.password != null) {
            Text(
                text = formErrors.password!!,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )
        }

        // Progress Bar
        if (loginState is LoginState.Loading) {
            CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
        }

        // Botón Login
        Button(
            onClick = {
                viewModel.login(email, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            enabled = loginState !is LoginState.Loading
        ) {
            Text(
                text = if (loginState is LoginState.Loading) {
                    "Iniciando sesión..."
                } else {
                    "Iniciar Sesión"
                },
                fontSize = 16.sp
            )
        }

        // Link a registro
        Text(
            text = "¿No tienes una cuenta? Regístrate aquí",
            color = Color(0xFF2196F3),
            modifier = Modifier
                .clickable { onNavigateToRegister() }
                .padding(16.dp)
        )

        // Info administradores
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Accesos de Administrador:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "mati.vegaa@duocuc.cl / adminmatias",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "fe.salazarv@duocuc.cl / adminfelipe",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "aa.lorca@duocuc.cl / adminaron",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}