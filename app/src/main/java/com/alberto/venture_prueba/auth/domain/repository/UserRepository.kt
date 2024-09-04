package com.alberto.venture_prueba.auth.domain.repository

import com.alberto.venture_prueba.auth.data.remote.UserResponse

interface UserRepository {
    suspend fun login(email: String, password: String): Result<UserResponse>
    suspend fun saveUserData(userResponse: UserResponse)
}
