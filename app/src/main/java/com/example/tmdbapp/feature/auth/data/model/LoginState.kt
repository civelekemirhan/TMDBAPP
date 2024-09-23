package com.example.tmdbapp.feature.auth.data.model

data class LoginState (
    val loginMail:String="",
    val loginPassword:String="",
    val loginMessage:String?=null,
    val loginSuccess:Boolean=false
)