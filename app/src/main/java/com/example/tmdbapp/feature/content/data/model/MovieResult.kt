package com.example.tmdbapp.feature.content.data.model

sealed class MovieResult(){

    data class Success(val movies:List<TmdbMovie>):MovieResult()
    data class Error(val errorCode:Int,val errorMessage:String,val emptyMovieList:List<TmdbMovie>):MovieResult()

}