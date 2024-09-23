package com.example.tmdbapp.common.component.authcomponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tmdbapp.feature.content.data.model.ContentEvent
import com.example.tmdbapp.feature.content.data.model.ContentViewModel
import com.example.tmdbapp.feature.content.data.model.MovieType
import com.example.tmdbapp.feature.content.data.model.TmdbMovieState

@Composable
fun ContentMovieTypeDialog(onEvent: (ContentEvent) -> Unit,state:TmdbMovieState) {

    AlertDialog(
        onDismissRequest = {
            onEvent(ContentEvent.HideDialog)
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { onEvent(ContentEvent.SaveMovieType) }) {
                    Text(text = "Save")
                }

            }
        },
        title = { Text(text = "Choose Movie Type") },
        text = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        MovieType.values().forEach { movieType ->

                            Row(modifier = Modifier.clickable(
                                onClick = {
                                    onEvent(ContentEvent.SetMovieType(movieType))
                                }
                            ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(selected = state.movieType == movieType, onClick = {
                                    onEvent(ContentEvent.SetMovieType(movieType))
                                })
                                Text(text = movieType.name)
                            }

                        }

                    }
                }
            }
        })


}