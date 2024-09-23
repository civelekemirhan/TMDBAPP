package com.example.tmdbapp.feature.auth.data.model

import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val firebaseDb: FirebaseOperations) :AuthRepository {
    override suspend fun login(email: String, password: String): Result<Boolean> {
      return firebaseDb.loginUser(email,password)
    }

    override suspend fun register(userName: String, email: String, password: String): Result<Boolean> {
       return firebaseDb.registerUser(userName,email,password)
    }

}

