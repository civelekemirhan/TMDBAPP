package com.example.tmdbapp.feature.auth.data.model

data class User(
    val id:Int=0,
    val mail:String,
    val username:String="",
    val pass:String
)