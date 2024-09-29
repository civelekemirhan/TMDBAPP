package com.example.tmdbapp.feature.content.data.room

data class TmdbSaveState(
    val movies: List<TmdbSave> = emptyList(),
    val title: String = "",
    val voteAverage: Float = 0.0f,
    val posterPath: String = "",
    val overView: String = "",
    val isFilterDialogShowing:Boolean = false,
    val isSaved: Boolean = false,
    var movieSavesType: String = MovieSavesType.ALL.name,
)