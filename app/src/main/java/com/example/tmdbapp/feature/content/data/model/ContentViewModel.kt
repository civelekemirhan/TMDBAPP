package com.example.tmdbapp.feature.content.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {



    private val _movieType = MutableStateFlow(MovieType.POPULAR)
    private val _movies = _movieType
        .flatMapLatest { movieType ->
            when (movieType) {
                MovieType.POPULAR -> contentRepository.getPopularMovies()
                MovieType.UPCOMING -> contentRepository.getUpcomingMovies()
                MovieType.TOP_RATED -> contentRepository.getTopRatedMovies()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())



   private val _contentState= MutableStateFlow(TmdbMovieState())
    val contentState = combine(_contentState,_movieType,_movies) { contentState, movieType, movies ->

                    contentState.copy(
                        movies = movies,
                        movieType = movieType
                    )

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

            ContentEvent.HideDialog ->  {
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