package com.example.tmdbapp.feature.content.data.model

import com.example.tmdbapp.feature.content.data.room.TmdbSave

sealed interface ContentEvent {

    object SaveMovieType : ContentEvent
    data class SetMovieType(val movieType: MovieType) : ContentEvent
    object ShowDialog:ContentEvent
    object HideDialog:ContentEvent





}