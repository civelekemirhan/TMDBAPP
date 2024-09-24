package com.example.tmdbapp.feature.content.data.model

import kotlinx.coroutines.flow.Flow


interface ContentRepository {

    suspend fun getPopularMovies(): Flow<MovieResult>
    suspend fun getTopRatedMovies(): Flow<MovieResult>
    suspend fun getUpcomingMovies(): Flow<MovieResult>

}