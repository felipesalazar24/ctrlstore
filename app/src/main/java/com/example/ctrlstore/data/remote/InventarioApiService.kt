package com.example.ctrlstore.data.remote

import com.example.ctrlstore.domain.model.Product
import retrofit2.Response
import retrofit2.http.*

interface InventarioApiService {

    @GET("api/productos")
    suspend fun obtenerProductos(): Response<List<Product>>
}
