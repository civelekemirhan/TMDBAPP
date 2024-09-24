package com.example.tmdbapp.feature.content.data.model

import com.example.tmdbapp.common.di.ConstantsDi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    ContentRepository {
    override suspend fun getPopularMovies(): Flow<MovieResult> {
        return flow {
            var response : Response<TmdbResponse>?

            try{
                response = apiService.getPopularMovies(ConstantsDi.BEARER_TOKEN)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.results?.let { emit(MovieResult.Success(it)) }
                } else {
                    emit(
                        MovieResult.Error(
                            response.code(), response.message(),
                            emptyList()
                        )
                    )
                }
            }catch(e:Exception){
                emit(
                    MovieResult.Error(
                        0, "Something went wrong",
                        emptyList()
                    )
                )
            }


        }.flowOn(Dispatchers.IO)

    }

    override suspend fun getTopRatedMovies(): Flow<MovieResult> {
        return flow {
            var response : Response<TmdbResponse>?

            try{
                response = apiService.getTopRatedMovies(ConstantsDi.BEARER_TOKEN)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.results?.let { emit(MovieResult.Success(it)) }
                } else {
                    emit(
                        MovieResult.Error(
                            response.code(), response.message(),
                            emptyList()
                        )
                    )
                }
            }catch(e:Exception){
                emit(
                    MovieResult.Error(
                        0, "Something went wrong",
                        emptyList()
                    )
                )
            }


        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUpcomingMovies(): Flow<MovieResult> {
        return flow {
            var response : Response<TmdbResponse>?

            try{
                response = apiService.getUpcomingMovies(ConstantsDi.BEARER_TOKEN)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.results?.let { emit(MovieResult.Success(it)) }
                } else {
                    emit(
                        MovieResult.Error(
                            response.code(), response.message(),
                            emptyList()
                        )
                    )
                }
            }catch(e:Exception){
                emit(
                    MovieResult.Error(
                        0, "Something went wrong",
                        emptyList()
                    )
                )
            }


        }.flowOn(Dispatchers.IO)
    }
}