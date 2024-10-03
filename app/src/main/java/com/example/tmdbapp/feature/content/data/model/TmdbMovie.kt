package com.example.tmdbapp.feature.content.data.model

import com.google.gson.annotations.SerializedName

data class TmdbMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val vote_average: Double,

)