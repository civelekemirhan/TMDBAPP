package com.example.tmdbapp.feature.content.data.room

import com.example.tmdbapp.feature.content.data.model.ContentEvent

sealed interface SaveScreenEvent {

    object ShowDialog : SaveScreenEvent
    object HideDialog : SaveScreenEvent
    object SaveMovieType : SaveScreenEvent
    data class SetMovieType(val movieType: String) : SaveScreenEvent
    data class DeleteMovie(val tmbdSave: TmdbSave) : SaveScreenEvent
    data class SaveMovie(val title: String, val overview: String, val posterPath: String, val voteAverage: Float, val movieType: String) : SaveScreenEvent



}