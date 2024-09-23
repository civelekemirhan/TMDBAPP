package com.example.tmdbapp.common.di

import com.example.tmdbapp.feature.content.data.model.ApiService
import com.example.tmdbapp.feature.content.data.model.ContentRepository
import com.example.tmdbapp.feature.content.data.model.ContentRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit {

        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }


        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()


       val retrofit= Retrofit.Builder()
            .baseUrl(ConstantsDi.apiReference)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideContentRepository(apiService: ApiService): ContentRepository {
        return ContentRepositoryImpl(apiService)
    }

}