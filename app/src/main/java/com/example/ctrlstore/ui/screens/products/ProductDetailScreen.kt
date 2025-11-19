package com.example.ctrlstore.ui.screens.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ctrlstore.domain.model.Product
import com.example.ctrlstore.viewmodel.ProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Int,
    onNavigateBack: () -> Unit,
    onNavigateToCart: () -> Unit
) {
    val viewModel: ProductsViewModel = viewModel()
    val products = remember { viewModel.getAllProducts() }
    val product: Product? = remember { products.find { it.id == productId } }

    if (product == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Producto no encontrado",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = onNavigateBack,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Volver a Productos")
            }
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = product.nombre,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
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
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    // Aquí iría la lógica para agregar al carrito
                    onNavigateToCart()
                },
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Agregar al carrito"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Agregar al Carrito")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Imagen principal del producto
            Image(
                painter = rememberAsyncImagePainter(
                    model = product.imagen,
                    error = painterResource(android.R.drawable.ic_menu_report_image)
                ),
                contentDescription = product.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Miniaturas
            if (product.miniaturas.isNotEmpty()) {
                Text(
                    text = "Galería:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(product.miniaturas) { miniatura ->
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = miniatura,
                                error = painterResource(android.R.drawable.ic_menu_report_image)
                            ),
                            contentDescription = "Miniatura",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Información del producto
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Precio
                Text(
                    text = "$${product.precio}",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196F3)
                )

                Spacer(modifier = Modifier.height(8.dp))


                Surface(
                    color = when (product.atributo) {
                        "Mouse" -> Color(0xFFFF9800)
                        "Teclado" -> Color(0xFF4CAF50)
                        "Audifono" -> Color(0xFF9C27B0)
                        "Monitor" -> Color(0xFF2196F3)
                        else -> Color.Gray
                    },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = product.atributo,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Descripción
                Text(
                    text = "Descripción:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.descripcion,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Especificaciones
                Text(
                    text = "Especificaciones:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    SpecificationItem("Marca", product.nombre.split(" ")[0])
                    SpecificationItem("Modelo", product.nombre)
                    SpecificationItem("Categoría", product.atributo)
                    SpecificationItem("Garantía", "1 año")
                    SpecificationItem("Disponibilidad", "En stock")
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun SpecificationItem(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}