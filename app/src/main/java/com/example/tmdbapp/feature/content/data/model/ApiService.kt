package com.example.tmdbapp.feature.content.data.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("popular")
    suspend fun getPopularMovies(@Header("Authorization") apiKey: String): Response<TmdbResponse>

    @GET("top_rated")
    suspend fun getTopRatedMovies(@Header("Authorization") apiKey: String): Response<TmdbResponse>

    @GET("upcoming")
    suspend fun getUpcomingMovies(@Header("Authorization") apiKey: String): Response<TmdbResponse>


}