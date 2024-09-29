package com.example.tmdbapp.feature.content.data.room

import kotlinx.coroutines.flow.Flow

interface SaveRepository {

    suspend fun insertMovie(tmdbSave: TmdbSave)
    suspend fun deleteMovie(tmdbSave: TmdbSave)
    fun getMovieByVoteAverage(): Flow<List<TmdbSave>>
    fun getMovieBySearchQuery(searchQuery: String): Flow<List<TmdbSave>>
    fun getMovieBySortType(movieSavesType: String): Flow<List<TmdbSave>>
    fun getAllMovies(): Flow<List<TmdbSave>>

}