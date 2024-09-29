package com.example.tmdbapp.feature.content.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tmdb_saves_movies")
data class TmdbSave (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val posterPath: String,
    val overView: String,
    val voteAverage: Double,
    val movieSortType:String,
)