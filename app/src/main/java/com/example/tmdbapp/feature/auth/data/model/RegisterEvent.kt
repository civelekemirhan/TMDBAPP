package com.example.tmdbapp.feature.auth.data.model

import androidx.navigation.NavController

sealed interface RegisterEvent {

    data class SetRegisterMail(val mail: String) : RegisterEvent
    data class SetRegisterUserName(val userName: String) : RegisterEvent
    data class SetRegisterPass(val pass: String) : RegisterEvent
    data class SetRegisterRePass(val rePass: String) : RegisterEvent
    object RegisterUser : RegisterEvent


}