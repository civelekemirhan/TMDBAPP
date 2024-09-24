package com.example.tmdbapp.feature.content.data.room

data class TmdbSaveState(
    val movies: List<TmdbSave> = emptyList(),
    val title: String = "",
    val voteAverage: Float = 0.0f,
    val posterPath: String = "",
    val overView: String = "",
    val isSaved: Boolean = false,
    val moviewSavesType: String = MovieSavesType.ALL.name,

)