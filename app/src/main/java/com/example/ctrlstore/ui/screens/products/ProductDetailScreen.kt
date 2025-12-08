package com.example.ctrlstore.ui.screens.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ctrlstore.domain.model.Product
import com.example.ctrlstore.viewmodel.ProductsViewModel

@Composable
fun ProductDetailScreen(
    productId: Int,
    onBackClick: () -> Unit,
    onAddToCart: (Product) -> Unit,
    onNavigateToCart: () -> Unit
) {
    val productsViewModel: ProductsViewModel = viewModel()
    val productos by productsViewModel.productos.collectAsState()
    val isLoading by productsViewModel.isLoading.collectAsState()
    val error by productsViewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        productsViewModel.cargarProductos()
    }

    var selectedImageIndex by remember { mutableStateOf(0) }

    val product = productos.find { it.id == productId }

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
            }
            Text(
                text = "Detalle del Producto",
                style = MaterialTheme.typography.headlineSmall
            )
            IconButton(onClick = onNavigateToCart) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Ir al carrito")
            }
        }

        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error: $error")
                }
            }

            product == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Producto no encontrado")
                }
            }

            else -> {
                // ░░ Contenido del detalle ░░
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    // ░ Imagen principal ░
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        val imageUrl = if (product.miniaturas.isNotEmpty()) {
                            product.miniaturas.getOrNull(selectedImageIndex) ?: product.imagen
                        } else {
                            product.imagen
                        }

                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Imagen de ${product.nombre}",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    if (product.miniaturas.isNotEmpty()) {
                        Text(
                            text = "Más imágenes:",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            product.miniaturas.forEachIndexed { index, thumbnailUrl ->
                                Card(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clickable { selectedImageIndex = index },
                                    elevation = CardDefaults.cardElevation(4.dp),
                                    border = if (selectedImageIndex == index) {
                                        CardDefaults.outlinedCardBorder()
                                    } else {
                                        null
                                    }
                                ) {
                                    AsyncImage(
                                        model = thumbnailUrl,
                                        contentDescription = "Miniatura ${index + 1}",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    Text(
                        text = product.nombre,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = product.precioFormateado,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = product.descripcion,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Categoría: ${product.atributo}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { onAddToCart(product) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Agregar al Carrito - ${product.precioFormateado}")
                    }
                }
            }
        }
    }
}
