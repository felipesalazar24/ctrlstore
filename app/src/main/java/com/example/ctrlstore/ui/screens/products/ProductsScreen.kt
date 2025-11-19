package com.example.ctrlstore.ui.screens.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ctrlstore.viewmodel.ProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToProductDetail: (Int) -> Unit
) {
    val viewModel: ProductsViewModel = viewModel()
    val products = remember { viewModel.getAllProducts() }
    val categories = remember { viewModel.getCategories() }

    var selectedCategory by remember { mutableStateOf("Todos") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        TopAppBar(
            title = {
                Text(
                    text = "Productos",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateToHome) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
            },
            actions = {
                IconButton(onClick = onNavigateToCart) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Carrito",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF2196F3)
            )
        )

        // Filtros por categoría
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Botón "Todos"
            item {
                FilterChip(
                    selected = selectedCategory == "Todos",
                    onClick = { selectedCategory = "Todos" },
                    label = { Text("Todos") }
                )
            }

            // Botones por categoría
            items(categories) { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    label = { Text(category) }
                )
            }
        }

        // Lista de productos
        val filteredProducts = if (selectedCategory == "Todos") {
            products
        } else {
            products.filter { it.atributo == selectedCategory }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredProducts) { product ->
                ProductCard(
                    product = product,
                    onProductClick = { onNavigateToProductDetail(product.id) }
                )
            }
        }
    }
}

@Composable
fun ProductCard(
    product: com.example.ctrlstore.domain.model.Product,
    onProductClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProductClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Imagen del producto
            Image(
                painter = rememberAsyncImagePainter(
                    model = product.imagen,
                    error = painterResource(android.R.drawable.ic_menu_report_image)
                ),
                contentDescription = product.nombre,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Información del producto
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = product.descripcion,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$${product.precio}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2196F3)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = product.atributo,
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier
                            .background(
                                color = when (product.atributo) {
                                    "Mouse" -> Color(0xFFFF9800)
                                    "Teclado" -> Color(0xFF4CAF50)
                                    "Audifono" -> Color(0xFF9C27B0)
                                    "Monitor" -> Color(0xFF2196F3)
                                    else -> Color.Gray
                                },
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}