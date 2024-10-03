package com.example.tmdbapp.feature.content.data.model


import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.feature.content.data.room.MovieSavesType
import com.example.tmdbapp.feature.content.data.room.SaveRepository
import com.example.tmdbapp.feature.content.data.room.SaveScreenEvent
import com.example.tmdbapp.feature.content.data.room.TmdbSave
import com.example.tmdbapp.feature.content.data.room.TmdbSaveState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
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

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _savedIcon=MutableStateFlow(Icons.Outlined.Favorite)
    private val savedIcon=_savedIcon.asStateFlow()


    @OptIn(ExperimentalCoroutinesApi::class)
    private val _savedMovies = _movieType.flatMapLatest { movieType ->
        when (movieType) {
            MovieSavesType.ALL.name -> saveRepository.getAllMovies()
                .map { it.sortedByDescending { saveMovie-> saveMovie.voteAverage } }

            MovieSavesType.POPULAR.name -> saveRepository.getMovieBySortType(MovieSavesType.POPULAR.name)
                .map { it.sortedByDescending { saveMovie-> saveMovie.voteAverage } }

            MovieSavesType.TOP_RATED.name -> saveRepository.getMovieBySortType(MovieSavesType.TOP_RATED.name)
                .map { it.sortedByDescending { saveMovie-> saveMovie.voteAverage } }

            MovieSavesType.UPCOMING.name -> saveRepository.getMovieBySortType(MovieSavesType.UPCOMING.name)
                .map { it.sortedByDescending { saveMovie-> saveMovie.voteAverage } }

            else -> flowOf(emptyList())
        }
    }.combine(_searchQuery) { movieList, query ->
        if (query.isNotEmpty()) {
            movieList.filter { it.title.contains(query, ignoreCase = true) }
        } else {
            movieList
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

                    if (saveRepository.getMovieWithTitle(saveScreenEvent.title)?.title == null) {
                        _savedIcon.value=Icons.Default.Favorite
                        val tmbdSave = TmdbSave(
                            title = saveScreenEvent.title,
                            overView = saveScreenEvent.overview,
                            posterPath = saveScreenEvent.posterPath,
                            voteAverage = saveScreenEvent.voteAverage.toDouble(),
                            movieSortType = saveScreenEvent.movieType,
                        )
                        saveRepository.insertMovie(tmbdSave)


                    } else {
                        Log.d("SaveViewModel", "SaveMovie: ${saveScreenEvent.title} already saved")
                    }

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

            is SaveScreenEvent.SearchMovie -> {
                _searchQuery.value = saveScreenEvent.query
            }
        }
    }

    fun savedBtnIcon(title: String): ImageVector {

        viewModelScope.launch {
            if (saveRepository.getMovieWithTitle(title)?.title != null) {
                _savedIcon.value=Icons.Default.Favorite
            } else {
                _savedIcon.value=Icons.Outlined.FavoriteBorder
            }
        }

        return savedIcon.value
    }

}


