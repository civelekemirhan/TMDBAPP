package com.example.tmdbapp.feature.content.data.room

import kotlinx.coroutines.flow.Flow

interface SaveRepository {

    suspend fun insertMovie(tmdbSave: TmdbSave)
    suspend fun deleteMovie(tmdbSave: TmdbSave)
    fun getAllMovie(): Flow<List<TmdbSave>>
    fun getMovieByVoteAverage(): Flow<List<TmdbSave>>
    fun getMovieByTitle(): Flow<List<TmdbSave>>
    fun getMovieBySortType(): Flow<List<TmdbSave>>

}