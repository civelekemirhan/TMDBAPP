package com.example.tmdbapp.feature.content.data.model

import com.example.tmdbapp.feature.content.data.network.TmdbMovie
import kotlinx.coroutines.flow.Flow


interface ContentRepository {

    suspend fun getPopularMovies(): Flow<List<TmdbMovie>>
    suspend fun getTopRatedMovies(): Flow<List<TmdbMovie>>
    suspend fun getUpcomingMovies(): Flow<List<TmdbMovie>>

}