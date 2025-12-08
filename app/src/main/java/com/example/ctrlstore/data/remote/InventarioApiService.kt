package com.example.ctrlstore.data.remote

import com.example.ctrlstore.domain.model.RemoteProduct
import retrofit2.Response
import retrofit2.http.*

interface InventarioApiService {

    @GET("api/productos")
    suspend fun getProductos(): Response<List<RemoteProduct>>
}
