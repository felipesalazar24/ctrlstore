package com.example.ctrlstore.ui.screens.products

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ctrlstore.domain.model.Product
import com.example.ctrlstore.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(
    onBackClick: () -> Unit,
    onProductClick: (Product) -> Unit,
    onNavigateToCart: () -> Unit,
    onCategoryClick: (String) -> Unit
) {
    val productsViewModel: ProductsViewModel = viewModel()
    val allProducts by productsViewModel.products.collectAsState()
    val isLoading by productsViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        if (allProducts.isEmpty()) {
            productsViewModel.fetchProducts()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) { Icon(Icons.Default.ArrowBack, "Volver") }
            Text("Productos", style = MaterialTheme.typography.headlineSmall)
            IconButton(onClick = onNavigateToCart) { Icon(Icons.Default.ShoppingCart, "Carrito") }
        }

        CategoryDropdown(onCategorySelected = { onCategoryClick(it) })
        Spacer(modifier = Modifier.height(8.dp))

        when {
            isLoading -> Box(Modifier.fillMaxSize().weight(1f), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            allProducts.isEmpty() -> Box(Modifier.fillMaxSize().weight(1f), contentAlignment = Alignment.Center) { Text("No hay productos") }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(allProducts) { product ->
                        ProductItem(product = product, onProductClick = onProductClick)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onProductClick: (Product) -> Unit) {
    Card(
        onClick = { onProductClick(product) },
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = product.imagenUrl,
                contentDescription = product.nombre,
                modifier = Modifier.fillMaxWidth().height(200.dp).clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Textos directos
            Text(product.nombre, style = MaterialTheme.typography.titleMedium, maxLines = 2)
            Text("$ ${product.precio}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Text(product.descripcion, style = MaterialTheme.typography.bodySmall, maxLines = 2)
            Text(product.atributo, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdown(onCategorySelected: (String) -> Unit) {
    val categories = listOf("Mouse", "Teclado", "Audifono", "Monitor")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Filtrar por categoría") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            label = { Text("Categorías") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            categories.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedText = option
                        expanded = false
                        onCategorySelected(option)
                    }
                )
            }
        }
    }
}