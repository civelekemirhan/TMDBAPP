package com.example.tmdbapp


import android.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel:ViewModel() {

    private val _navigationBarColor= MutableStateFlow(Color.argb(0x80, 0x00, 0x00, 0x00))
    val navigationBarColor=_navigationBarColor.asStateFlow()

    private val _statusBarColor= MutableStateFlow(Color.TRANSPARENT)
    val statusBarColor=_statusBarColor.asStateFlow()


}