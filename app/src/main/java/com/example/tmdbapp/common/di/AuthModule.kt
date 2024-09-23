package com.example.tmdbapp.common.di

import com.example.tmdbapp.feature.auth.data.model.AuthRepository
import com.example.tmdbapp.feature.auth.data.model.AuthRepositoryImpl
import com.example.tmdbapp.feature.auth.data.model.FirebaseOperations
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseOperations(): FirebaseOperations {
        return FirebaseOperations()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseOperations: FirebaseOperations): AuthRepository {
        return AuthRepositoryImpl(firebaseOperations)
    }
}