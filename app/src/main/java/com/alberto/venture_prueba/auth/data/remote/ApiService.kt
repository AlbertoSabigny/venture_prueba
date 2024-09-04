package com.alberto.venture_prueba.auth.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("Authorization/AccesoUsuario")
    suspend fun accesoUsuario(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<UserResponse>

    @POST("AdministracionEmpleados/BusquedaEmpleado")
    suspend fun buscarEmpleados(
        @Header("Authorization") token: String,
        @Body body: EmpleadoRequest
    ): Response<EmpleadoResponse>
}