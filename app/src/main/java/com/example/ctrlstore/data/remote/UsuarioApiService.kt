package com.example.ctrlstore.data.remote

import com.example.ctrlstore.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioApiService {

    @GET("api/v1/usuarios")
    suspend fun getUsuarios(): List<User>

    @GET("api/v1/usuarios/id/{id}")
    suspend fun getUsuarioPorId(
        @Path("id") id: Int
    ): User

    @POST("api/v1/usuarios")
    suspend fun createUsuario(
        @Body user: User
    ): User

    @PUT("api/v1/usuarios/actualizar/{id}")
    suspend fun updateUsuario(
        @Path("id") id: Int,
        @Body user: User
    ): User

    @DELETE("api/v1/usuarios/id/{id}")
    suspend fun deleteUsuario(
        @Path("id") id: Int
    ): Response<Unit>
}
