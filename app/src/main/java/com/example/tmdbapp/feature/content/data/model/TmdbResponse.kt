package com.example.tmdbapp.feature.content.data.model

import com.google.gson.annotations.SerializedName

data class TmdbResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TmdbMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int

)