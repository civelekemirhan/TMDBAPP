package com.example.tmdbapp.feature.content.data.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.feature.content.data.room.SaveRepository
import com.example.tmdbapp.feature.content.data.room.TmdbSave
import com.example.tmdbapp.feature.content.data.room.TmdbSaveState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val contentRepository: ContentRepository,
    private val saveRepository: SaveRepository
) : ViewModel() {


    private val _movieType = MutableStateFlow(MovieType.POPULAR)
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _movies = _movieType
        .flatMapLatest { movieType ->
            when (movieType) {
                MovieType.POPULAR -> contentRepository.getPopularMovies()
                MovieType.UPCOMING -> contentRepository.getUpcomingMovies()
                MovieType.TOP_RATED -> contentRepository.getTopRatedMovies()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            MovieResult.Error(0,"",emptyList())
        )


    private val _contentState = MutableStateFlow(TmdbMovieState())
    val contentState =
        combine(_contentState, _movieType, _movies) { contentState, movieType, movies ->

            when (movies) {
                is MovieResult.Success -> {

                    contentState.copy(
                        movies = movies.movies,
                        movieType = movieType,
                    )
                }

                is MovieResult.Error -> {

                    contentState.copy(
                        movies = movies.emptyMovieList,
                        movieType = movieType,
                        errorMessage = movies.errorMessage,
                        code = movies.errorCode
                    )
                }
            }


        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TmdbMovieState())


    fun onEvent(event: ContentEvent) {
        when (event) {
            ContentEvent.SaveMovieType -> {
                _contentState.update {
                    it.copy(
                        movieType = contentState.value.movieType,
                        isSortTypeShowing = false
                    )
                }
            }

            ContentEvent.HideDialog -> {
                _contentState.update {
                    it.copy(
                        isSortTypeShowing = false
                    )
                }
            }

            ContentEvent.ShowDialog -> {
                _contentState.update {
                    it.copy(
                        isSortTypeShowing = true
                    )
                }
            }

            is ContentEvent.SetMovieType -> {
                _movieType.value = event.movieType
            }
        }
    }


}