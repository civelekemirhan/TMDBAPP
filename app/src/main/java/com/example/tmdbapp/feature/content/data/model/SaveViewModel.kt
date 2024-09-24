package com.example.tmdbapp.feature.content.data.model

import androidx.lifecycle.ViewModel
import com.example.tmdbapp.feature.content.data.room.SaveRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SaveViewModel @Inject constructor(private val saveRepository: SaveRepository): ViewModel() {




}