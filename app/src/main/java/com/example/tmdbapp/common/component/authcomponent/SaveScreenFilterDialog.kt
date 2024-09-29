package com.example.tmdbapp.common.component.authcomponent

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdbapp.feature.content.data.model.ContentEvent
import com.example.tmdbapp.feature.content.data.model.MovieType
import com.example.tmdbapp.feature.content.data.model.SaveViewModel
import com.example.tmdbapp.feature.content.data.model.TmdbMovieState
import com.example.tmdbapp.feature.content.data.room.MovieSavesType
import com.example.tmdbapp.feature.content.data.room.SaveScreenEvent
import com.example.tmdbapp.feature.content.data.room.TmdbSaveState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SaveScreenFilterDialog(searchQuery:StateFlow<String>, onEvent: (SaveScreenEvent) -> Unit, state: TmdbSaveState){

    val searchQuery by searchQuery.collectAsState()


    AlertDialog(
        onDismissRequest = {
            onEvent(SaveScreenEvent.HideDialog)
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { onEvent(SaveScreenEvent.SaveMovieType) }) {
                    Text(text = "Save")
                }

            }
        },
        title = { Text(text = "Filter Saves") },
        text = {
            Column(){
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {
                            MovieSavesType.values().forEach { movieType ->

                                Row(modifier = Modifier.clickable(
                                    onClick = {
                                        onEvent(SaveScreenEvent.SetMovieType(movieType.name))
                                    }
                                ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(selected = state.movieSavesType == movieType.name, onClick = {
                                        Log.d("TAG", "SaveScreenFilterDialog: ${state.movieSavesType}=${movieType.name}")
                                        onEvent(SaveScreenEvent.SetMovieType(movieType.name))
                                    })
                                    Text(text = movieType.name)
                                }

                            }

                        }
                    }
                }
                Column {
                    OutlinedTextField(value = searchQuery, onValueChange = {
                        onEvent(SaveScreenEvent.SearchMovie(it))
                    },label={
                        Text(text = "Anahtar kelime giriniz")
                    })
                }
            }
        })

}