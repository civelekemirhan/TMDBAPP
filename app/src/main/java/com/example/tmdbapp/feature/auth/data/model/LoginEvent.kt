package com.example.tmdbapp.feature.auth.data.model

import androidx.navigation.NavController

sealed interface LoginEvent {

    data class SetLoginMail(val mail: String):LoginEvent
    data class SetLoginPass(val pass: String):LoginEvent
    object LoginUser : LoginEvent
}