package com.example.ctrlstore.ui.screens.products

import androidx.compose.foundation.Image
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
import coil.compose.rememberAsyncImagePainter
import com.example.ctrlstore.viewmodel.ProductViewModel
import com.example.ctrlstore.viewmodel.ProductsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProductDetailScreen(
    productId: Int,
    onBackClick: () -> Unit,
    onAddToCart: (com.example.ctrlstore.domain.model.Product) -> Unit,
    onNavigateToCart: () -> Unit
) {
    val productViewModel: ProductViewModel = viewModel()
    val productsState by productViewModel.productsState.collectAsState()

    // Estado para la imagen principal seleccionada
    var selectedImageIndex by remember { mutableIntStateOf(0) }

    // Encontrar el producto por ID
    val product = when (productsState) {
        is ProductsState.Success -> {
            (productsState as ProductsState.Success)
                .products.find { it.id == productId }
        }
        else -> null
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Barra superior
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
                "Detalle del Producto",
                style = MaterialTheme.typography.headlineSmall
            )
            IconButton(onClick = onNavigateToCart) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Ir al carrito")
            }
        }

        if (product == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("Producto no encontrado")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // IMAGEN PRINCIPAL
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    val imageUrl = if (product.miniaturas.isNotEmpty()) {
                        // Si hay miniaturas, usa la seleccionada
                        product.miniaturas.getOrNull(selectedImageIndex) ?: product.imagen
                    } else {
                        // Si no hay miniaturas, usa la imagen principal
                        product.imagen
                    }

                    Image(
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentDescription = "Imagen de ${product.nombre}",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // MINIATURAS (si existen)
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
                                Image(
                                    painter = rememberAsyncImagePainter(model = thumbnailUrl),
                                    contentDescription = "Miniatura ${index + 1}",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // INFORMACIÓN DEL PRODUCTO
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

                // BOTÓN AGREGAR AL CARRITO
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