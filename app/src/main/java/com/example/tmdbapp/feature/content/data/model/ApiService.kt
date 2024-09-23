package com.example.tmdbapp.feature.content.data.model

import com.example.tmdbapp.feature.content.data.network.TmdbMovie
import com.example.tmdbapp.feature.content.data.network.TmdbResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("popular")
    suspend fun getPopularMovies(@Header("Authorization") apiKey: String): TmdbResponse

    @GET("top_rated")
    suspend fun getTopRatedMovies(@Header("Authorization") apiKey: String): TmdbResponse

    @GET("upcoming")
    suspend fun getUpcomingMovies(@Header("Authorization") apiKey: String): TmdbResponse


}