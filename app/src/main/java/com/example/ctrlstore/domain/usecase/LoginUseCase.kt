package com.example.ctrlstore.domain.usecase

import com.example.ctrlstore.data.repository.AuthRepository
import com.example.ctrlstore.domain.model.LoginResponse

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): LoginResponse {
        return authRepository.login(email, password)
    }
}