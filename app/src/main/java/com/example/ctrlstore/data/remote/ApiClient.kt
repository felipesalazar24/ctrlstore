package com.example.ctrlstore.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL_USUARIO = "http://192.168.18.116:8080/"
    private const val BASE_URL_INVENTARIO = "http://192.168.18.116:8081/"
    //private const val BASE_URL_TRANSACCIONES = "http://localhost:8083/"

    private val inventarioRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_INVENTARIO)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val inventarioApi: InventarioApiService by lazy {
        inventarioRetrofit.create(InventarioApiService::class.java)
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val usuarioApi: UsuarioApiService =
        getRetrofit(BASE_URL_USUARIO).create(UsuarioApiService::class.java)

    /*
    val transaccionesApi: TransaccionesApiService =
        getRetrofit(BASE_URL_TRANSACCIONES).create(TransaccionesApiService::class.java)
     */
}
