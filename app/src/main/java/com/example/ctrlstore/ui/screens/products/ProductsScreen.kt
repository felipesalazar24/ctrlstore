package com.example.ctrlstore.ui.screens.products

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ctrlstore.ui.components.NetworkImage
import com.example.ctrlstore.viewmodel.ProductViewModel
import com.example.ctrlstore.viewmodel.ProductsState

@Composable
fun ProductsScreen(
    onBackClick: () -> Unit,
    onProductClick: (com.example.ctrlstore.domain.model.Product) -> Unit
) {
    val productViewModel: ProductViewModel = viewModel()
    val productsState by productViewModel.productsState.collectAsState()

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
                "Todos los Productos",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.width(48.dp))
        }

        when (productsState) {
            is ProductsState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ProductsState.Error -> {
                val errorState = productsState as ProductsState.Error
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Error: ${errorState.message}")
                        Button(
                            onClick = { productViewModel.loadProducts() },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text("Reintentar")
                        }
                    }
                }
            }

            is ProductsState.Success -> {
                val successState = productsState as ProductsState.Success
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(successState.products) { product ->
                        ProductItem(
                            product = product,
                            onProductClick = onProductClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    product: com.example.ctrlstore.domain.model.Product,
    onProductClick: (com.example.ctrlstore.domain.model.Product) -> Unit
) {
    Card(
        onClick = { onProductClick(product) },
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // IMAGEN DEL PRODUCTO
            NetworkImage(
                imageUrl = product.imagen,
                contentDescription = "Imagen de ${product.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = product.nombre,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = product.precioFormateado,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = product.descripcion,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = product.atributo,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}