package com.alberto.venture_prueba.auth.domain.usecase

import com.alberto.venture_prueba.auth.data.remote.UserResponse
import com.alberto.venture_prueba.auth.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<UserResponse> {
        return userRepository.login(email, password)
    }
}