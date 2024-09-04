package com.alberto.venture_prueba.auth.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.alberto.venture_prueba.core.data.remote.ApiService
import com.alberto.venture_prueba.auth.data.remote.UserResponse
import com.alberto.venture_prueba.auth.domain.repository.UserRepository
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences
) : UserRepository {

    override suspend fun login(email: String, password: String): Result<UserResponse> {
        return try {
            val response = apiService.accesoUsuario(email, password)
            when {
                response.isSuccessful && response.body() != null -> {
                    val userResponse = response.body()!!
                    if (userResponse.error) {
                        Result.failure(LoginException(userResponse.codigo, "Error de servidor"))
                    } else {
                        saveUserData(userResponse)
                        Result.success(userResponse)
                    }
                }
                response.code() == 401 -> Result.failure(LoginException("401", "Credenciales inválidas"))
                else -> Result.failure(LoginException(response.code().toString(), "Error de red: ${response.message()}"))
            }
        } catch (e: IOException) {
            Result.failure(LoginException("IO_ERROR", "Error de conexión"))
        } catch (e: Exception) {
            Result.failure(LoginException("UNKNOWN", "Error desconocido: ${e.message}"))
        }
    }

    override suspend fun saveUserData(userResponse: UserResponse) {
        sharedPreferences.edit().apply()
        {
            putString("token", userResponse.token)
            putInt("idUsuario", userResponse.acceso.usuarioeTime.idUsuario)
            putInt("idCia", userResponse.acceso.usuarioeTime.scia.firstOrNull()?.cia ?: -1)
            putString("nombreCompleto", userResponse.acceso.usuarioeTime.nombreCompleto)
            putString("foto", userResponse.acceso.usuarioeTime.foto)
        }.apply()
        Log.d("UserProfile", "Perfil guardado: $userResponse")
    }
}

class LoginException(val errorCode: String, message: String) : Exception(message)
