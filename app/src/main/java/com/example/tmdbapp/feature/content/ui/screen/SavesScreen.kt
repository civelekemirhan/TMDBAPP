package com.example.tmdbapp.feature.content.ui.screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tmdbapp.common.component.authcomponent.ContentMovieTypeDialog
import com.example.tmdbapp.common.component.authcomponent.SaveScreenFilterDialog
import com.example.tmdbapp.feature.content.data.model.ContentEvent
import com.example.tmdbapp.feature.content.data.model.SaveViewModel
import com.example.tmdbapp.feature.content.data.room.SaveScreenEvent
import com.example.tmdbapp.navigation.NavigationConstant
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavesScreen(navController: NavController) {

    val savesViewModel: SaveViewModel = hiltViewModel()
    val state by savesViewModel.state.collectAsState()

    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                title = { Text(text = "Saves", fontSize = 30.sp, color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                actions = {
                    IconButton(onClick = { savesViewModel.onEvent(SaveScreenEvent.ShowDialog) }) {
                        Icon(
                            imageVector = Icons.Outlined.Build,
                            contentDescription = "Filter",
                            tint = Color.White
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(NavigationConstant.HOME_SCREEN_ROUTE_KEY) {
                            popUpTo(NavigationConstant.SAVES_SCREEN_ROUTE_KEY) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }


            )

        },
        containerColor = MaterialTheme.colorScheme.inverseOnSurface
    ) {

        if (state.isFilterDialogShowing) {
            SaveScreenFilterDialog(savesViewModel.searchQuery,savesViewModel::onEvent, state)
        }


        LazyColumn(
            modifier = Modifier
                .padding(it)
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {

            items(state.movies) { movie ->

                SaveMovieItem(movie = movie,savesViewModel::onEvent) {
                    val encodedTitle = Uri.encode(movie.title)
                    val encodedOverview = Uri.encode(movie.overView)
                    val encodedPosterPath = Uri.encode(movie.posterPath)

                    navController.navigate("${NavigationConstant.DETAILS_SCREEN_ROUTE_KEY}/$encodedTitle/$encodedOverview/$encodedPosterPath/${movie.voteAverage.toFloat()}/${state.movieSavesType}")
                }

            }


        }
    }


}