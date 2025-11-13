package com.example.ctrlstore.domain.usecase

import com.example.ctrlstore.data.repository.AuthRepository
import com.example.ctrlstore.domain.model.RegisterRequest
import  com.example.ctrlstore.domain.model.RegisterResponse

class RegisterUseCase (private val authRepository: AuthRepository){
    suspend operator fun invoke (registerRequest: RegisterRequest): RegisterResponse{
        return authRepository.register(registerRequest)
    }
}