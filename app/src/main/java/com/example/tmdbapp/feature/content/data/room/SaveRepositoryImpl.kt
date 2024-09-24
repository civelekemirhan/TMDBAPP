package com.example.tmdbapp.feature.content.data.room

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveRepositoryImpl @Inject constructor(private val dao: TmdbSaveDao): SaveRepository {
    override suspend fun insertMovie(tmdbSave: TmdbSave) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMovie(tmdbSave: TmdbSave) {
        TODO("Not yet implemented")
    }

    override fun getAllMovie(): Flow<List<TmdbSave>> {
        TODO("Not yet implemented")
    }

    override fun getMovieByVoteAverage(): Flow<List<TmdbSave>> {
        TODO("Not yet implemented")
    }

    override fun getMovieByTitle(): Flow<List<TmdbSave>> {
        TODO("Not yet implemented")
    }

    override fun getMovieBySortType(): Flow<List<TmdbSave>> {
        TODO("Not yet implemented")
    }


}