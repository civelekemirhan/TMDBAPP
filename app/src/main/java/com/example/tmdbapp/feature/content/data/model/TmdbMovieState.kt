package com.example.tmdbapp.feature.content.data.model

import com.example.tmdbapp.feature.content.data.network.TmdbMovie


data class TmdbMovieState(
    val movies: List<TmdbMovie> = emptyList(),

    val id: Int=0,

    val overview: String="",

    val poster_path: String="",

    val title: String="",

    val vote_average: Double=0.0,

    val movieType: MovieType = MovieType.POPULAR,

    val isSortTypeShowing:Boolean = false,
)