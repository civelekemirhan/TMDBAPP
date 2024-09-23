package com.example.tmdbapp.feature.auth.data.model

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Boolean>
    suspend fun register(userName: String, email: String, password: String): Result<Boolean>
}

