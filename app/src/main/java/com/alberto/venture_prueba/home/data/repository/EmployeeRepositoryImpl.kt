package com.alberto.venture_prueba.home.data.repository

import android.content.SharedPreferences
import com.alberto.venture_prueba.auth.data.remote.ApiService
import com.alberto.venture_prueba.auth.data.remote.EmpleadoRequest
import com.alberto.venture_prueba.auth.data.remote.EmpleadoResponse
import com.alberto.venture_prueba.home.domain.repository.EmployeeRepository
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences
) : EmployeeRepository {

    override suspend fun getEmployees(): Result<EmpleadoResponse> {
        val token = sharedPreferences.getString("token", null)
        val idUsuario = sharedPreferences.getInt("idUsuario", -1)
        val idCia = sharedPreferences.getInt("idCia", -1)

        if (token == null || idUsuario == -1 || idCia == -1) {
            return Result.failure(Exception("Datos insuficientes almacenados"))
        }

        val bearerToken = "Bearer $token"
        val requestBody = EmpleadoRequest(
            filtroEmpleado = "",
            idCia = idCia,
            idUsuario = idUsuario,
            numRegistros = 500,
            pagina = 1
        )

        return try {
            val response = apiService.buscarEmpleados(bearerToken, requestBody)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error en la llamada a la API de empleados"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}