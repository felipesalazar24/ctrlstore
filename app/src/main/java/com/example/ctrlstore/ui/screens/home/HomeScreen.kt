package com.example.ctrlstore.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onNavigateToProducts: () -> Unit,
    onNavigateToCart: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Bienvenido a CTRL Store!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3),
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Text(
            text = "Tu tienda de tecnología favorita",
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        Button(
            onClick = onNavigateToProducts,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            )
        ) {
            Text(
                text = "Ver Productos",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
        Button(
            onClick = onNavigateToCart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50)
            )
        ) {
            Text(
                text = "Ver Carrito",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
        Text(
            text = "Encuentra los mejores productos tecnológicos",
            fontSize = 14.sp,
            color = Color.LightGray,
            modifier = Modifier.padding(top = 48.dp)
        )
    }
}