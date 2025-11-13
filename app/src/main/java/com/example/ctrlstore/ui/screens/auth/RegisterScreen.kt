package com.example.ctrlstore.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ctrlstore.viewmodel.RegisterViewModel
import com.example.ctrlstore.viewmodel.RegisterState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(),
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val registerState by viewModel.registerState.collectAsState()
    val formErrors by viewModel.formErrors.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    // Manejar estado de éxito
    LaunchedEffect(registerState) {
        if (registerState is RegisterState.Success) {
            onRegisterSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Crear Cuenta",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 32.dp)
        )

        Text(
            text = "Regístrate para comenzar",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        if (registerState is RegisterState.Error) {
            val errorState = registerState as RegisterState.Error
            Text(
                text = errorState.message,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        Text(
            text = "Nombre Completo",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Start
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                viewModel.clearErrors()
            },
            placeholder = { Text("Tu nombre completo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            isError = formErrors.nombre != null
        )

        if (formErrors.nombre != null) {
            Text(
                text = formErrors.nombre!!,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )
        }
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
            placeholder = { Text("Mínimo 6 caracteres") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            visualTransformation = if (passwordVisible) {
                androidx.compose.ui.text.input.VisualTransformation.None
            } else {
                androidx.compose.ui.text.input.PasswordVisualTransformation()
            },
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            isError = formErrors.password != null
        )

        if (formErrors.password != null) {
            Text(
                text = formErrors.password!!,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )
        }
        Text(
            text = "Confirmar Contraseña",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Start
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                viewModel.clearErrors()
            },
            placeholder = { Text("Repite tu contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            visualTransformation = if (confirmPasswordVisible) {
                androidx.compose.ui.text.input.VisualTransformation.None
            } else {
                androidx.compose.ui.text.input.PasswordVisualTransformation()
            },
            trailingIcon = {
                val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (confirmPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"

                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            isError = formErrors.confirmPassword != null
        )

        if (formErrors.confirmPassword != null) {
            Text(
                text = formErrors.confirmPassword!!,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )
        }
        if (registerState is RegisterState.Loading) {
            CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
        }
        Button(
            onClick = {
                viewModel.register(nombre, email, password, confirmPassword)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            enabled = registerState !is RegisterState.Loading
        ) {
            Text(
                text = if (registerState is RegisterState.Loading) {
                    "Creando cuenta..."
                } else {
                    "Crear Cuenta"
                },
                fontSize = 16.sp
            )
        }
        Text(
            text = "¿Ya tienes una cuenta? Inicia sesión aquí",
            color = Color(0xFF2196F3),
            modifier = Modifier
                .clickable { onNavigateToLogin() }
                .padding(16.dp)
        )
    }
}