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

    @Query("SELECT * FROM tmdb_saves_movies ORDER BY voteAverage ASC")
    fun getMovieByVoteAverage(): Flow<List<TmdbSave>>

    @Query("SELECT * FROM tmdb_saves_movies ORDER BY title ASC")
    fun getMovieByTitle(): Flow<List<TmdbSave>>

    @Query("SELECT * FROM tmdb_saves_movies WHERE movieSortType = :movieSortType ORDER BY movieSortType ASC")
    fun getMovieBySortType(movieSortType: String): Flow<List<TmdbSave>>

    @Query("SELECT * FROM tmdb_saves_movies ")
    fun getAllMovie(): Flow<List<TmdbSave>>



}