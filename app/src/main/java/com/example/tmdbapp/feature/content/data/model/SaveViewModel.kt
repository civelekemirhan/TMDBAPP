package com.example.tmdbapp.feature.content.data.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.feature.content.data.room.MovieSavesType
import com.example.tmdbapp.feature.content.data.room.SaveRepository
import com.example.tmdbapp.feature.content.data.room.SaveScreenEvent
import com.example.tmdbapp.feature.content.data.room.TmdbSave
import com.example.tmdbapp.feature.content.data.room.TmdbSaveState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SaveViewModel @Inject constructor(private val saveRepository: SaveRepository) : ViewModel() {

    private val _movieType = MutableStateFlow(MovieSavesType.ALL.name)
    private val _savedMovies = _movieType
        .flatMapLatest { movieType ->
            when (movieType) {
                MovieSavesType.ALL.name -> saveRepository.getAllMovies()
                MovieSavesType.POPULAR.name -> saveRepository.getMovieBySortType(MovieSavesType.POPULAR.name)
                MovieSavesType.TOP_RATED.name -> saveRepository.getMovieBySortType(MovieSavesType.TOP_RATED.name)
                MovieSavesType.UPCOMING.name -> saveRepository.getMovieBySortType(MovieSavesType.UPCOMING.name)
                else -> flowOf(emptyList())
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    private val _state = MutableStateFlow(TmdbSaveState())
    val state = combine(_state, _movieType, _savedMovies) { state, movieType, savedMovies ->
        state.copy(
            movies = savedMovies,
            movieSavesType = movieType,
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TmdbSaveState())


    fun onEvent(saveScreenEvent: SaveScreenEvent) {
        when (saveScreenEvent) {
            is SaveScreenEvent.DeleteMovie -> {
                viewModelScope.launch {
                    saveRepository.deleteMovie(saveScreenEvent.tmbdSave)
                }
            }

            SaveScreenEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isFilterDialogShowing = false
                    )
                }
            }

            is SaveScreenEvent.SaveMovie -> {
                viewModelScope.launch {

                    val tmbdSave = TmdbSave(
                        title = saveScreenEvent.title,
                        overView = saveScreenEvent.overview,
                        posterPath = saveScreenEvent.posterPath,
                        voteAverage = saveScreenEvent.voteAverage.toDouble(),
                        movieSortType = saveScreenEvent.movieType
                    )
                    saveRepository.insertMovie(tmbdSave)

                }

            }

            is SaveScreenEvent.SetMovieType -> {
                _movieType.value = saveScreenEvent.movieType
            }

            SaveScreenEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isFilterDialogShowing = true
                    )
                }
            }

            SaveScreenEvent.SaveMovieType -> {
                _state.update {
                    it.copy(
                        movieSavesType = state.value.movieSavesType,
                        isFilterDialogShowing = false
                    )
                }
            }
        }
    }

}


