package com.example.tmdbapp.feature.content.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TmdbSaveDao {

    @Insert
    suspend fun insertMovie(tmdbSave: TmdbSave)

    @Delete
    suspend fun deleteMovie(tmdbSave: TmdbSave)

    @Query("SELECT * FROM tmdb_movie_saves")
    fun getAllMovie(): Flow<List<TmdbSave>>

    @Query("SELECT * FROM tmdb_movie_saves ORDER BY voteAverage ASC")
    fun getMovieByVoteAverage(): Flow<List<TmdbSave>>

    @Query("SELECT * FROM tmdb_movie_saves ORDER BY title ASC")
    fun getMovieByTitle(): Flow<List<TmdbSave>>

    @Query("SELECT * FROM tmdb_movie_saves ORDER BY movieSortType ASC")
    fun getMovieBySortType(): Flow<List<TmdbSave>>



}