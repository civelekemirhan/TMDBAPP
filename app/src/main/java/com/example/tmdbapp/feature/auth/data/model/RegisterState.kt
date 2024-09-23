package com.example.tmdbapp.feature.auth.data.model

data class RegisterState (
    val registerMail:String="",
    val registerUserName:String="",
    val registerPassword:String="",
    val registerRePassword:String="",
    val registerSuccess:Boolean=false,
    val registerLoading:Boolean=false,
    val registerMessage:String=""
)
