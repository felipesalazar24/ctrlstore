package com.example.ctrlstore.ui.screens.cart

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ctrlstore.data.local.CartItem
import com.example.ctrlstore.ui.components.NetworkImage
import com.example.ctrlstore.viewmodel.CartViewModel

@Composable
fun CartScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToProducts: () -> Unit,
    cartViewModel: CartViewModel = viewModel()
) {
    val context = LocalContext.current
    val cartState by cartViewModel.cart.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "🛒 Carrito de Compras",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Items: ${cartState.size}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        if (cartState.isEmpty()) {
            // Estado vacío: mostramos mensaje y botones para navegar (Home / Productos)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Tu carrito está vacío",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Agrega productos desde la sección de productos",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // Botones visibles cuando el carrito está vacío
                Column(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = onNavigateToProducts,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2196F3)
                        )
                    ) {
                        Text("Ir a Productos")
                    }

                    OutlinedButton(
                        onClick = onNavigateToHome,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)
                    ) {
                        Text("Volver al Inicio")
                    }
                }
            }
        } else {
            // Lista de items
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 8.dp)
            ) {
                items(cartState) { item: CartItem ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Imagen (si tienes NetworkImage componible)
                            NetworkImage(
                                imageUrl = item.imageUrl ?: "",
                                contentDescription = item.title,
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 12.dp)
                            )

                            Column(modifier = Modifier.weight(1f)) {
                                Text(item.title, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("${formatPrice(item.price)}", color = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Cantidad: ${item.quantity}", color = Color.Gray)
                            }

                            IconButton(onClick = { cartViewModel.removeItem(item.productId) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                            }
                        }
                    }
                }
            }

            // Total y acciones
            val total = cartState.fold(0.0) { acc, it -> acc + (it.price * it.quantity) }
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Total:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(formatPrice(total), fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MaterialTheme.colorScheme.primary)
                }

                Button(
                    onClick = {
                        // Aquí iría la lógica real de pago -> por ahora solo limpiamos
                        cartViewModel.clearCart()
                        Toast.makeText(context, "Pago realizado. Carrito vacío.", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                ) {
                    Text("Pagar")
                }

                OutlinedButton(
                    onClick = onNavigateToProducts,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Seguir comprando")
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onNavigateToHome,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)
                ) {
                    Text("Volver al Inicio")
                }
            }
        }
    }
}

private fun formatPrice(value: Double): String {
    val intPart = value.toInt()
    return "$${intPart.toString().replace(Regex("(\\d)(?=(\\d{3})+(?!\\d))"), "$1.")}"
}