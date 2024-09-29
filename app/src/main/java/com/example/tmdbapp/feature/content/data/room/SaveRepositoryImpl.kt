package com.example.tmdbapp.feature.content.data.room

import android.util.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveRepositoryImpl @Inject constructor(private val dao: TmdbSaveDao): SaveRepository {
    override suspend fun insertMovie(tmdbSave: TmdbSave) {
        dao.insertMovie(tmdbSave)
    }

    override suspend fun deleteMovie(tmdbSave: TmdbSave) {

        dao.deleteMovie(tmdbSave)

    }


    override fun getMovieByVoteAverage(): Flow<List<TmdbSave>> {
        return dao.getMovieByVoteAverage()
    }

    override fun getMovieBySearchQuery(searchQuery: String): Flow<List<TmdbSave>> {
        return dao.getMovieBySearchQuery(searchQuery)
    }

    override fun getMovieBySortType(movieSavesType: String): Flow<List<TmdbSave>> {
       return dao.getMovieBySortType(movieSavesType)
    }

    override fun getAllMovies(): Flow<List<TmdbSave>> {
        return dao.getAllMovie()
    }


}