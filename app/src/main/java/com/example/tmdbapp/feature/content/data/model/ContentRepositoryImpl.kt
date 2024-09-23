package com.example.tmdbapp.feature.content.data.model

import com.example.tmdbapp.common.di.ConstantsDi
import com.example.tmdbapp.feature.content.data.network.TmdbMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val apiService: ApiService):ContentRepository {
    override suspend fun getPopularMovies(): Flow<List<TmdbMovie>> {
        return flow {
            val response = apiService.getPopularMovies(ConstantsDi.BEARER_TOKEN)
            emit(response.results)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTopRatedMovies(): Flow<List<TmdbMovie>> {
        return flow {
            val response = apiService.getTopRatedMovies(ConstantsDi.BEARER_TOKEN)
            emit(response.results)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUpcomingMovies(): Flow<List<TmdbMovie>> {
        return flow {
            val response = apiService.getUpcomingMovies(ConstantsDi.BEARER_TOKEN)
            emit(response.results)
        }.flowOn(Dispatchers.IO)
    }
}