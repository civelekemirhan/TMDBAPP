package com.example.tmdbapp.feature.content.ui.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tmdbapp.common.component.authcomponent.ContentMovieTypeDialog
import com.example.tmdbapp.feature.content.data.model.ContentEvent
import com.example.tmdbapp.feature.content.data.model.ContentViewModel
import com.example.tmdbapp.navigation.NavigationConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,contentViewModel:ContentViewModel=hiltViewModel()) {

    val state by contentViewModel.contentState.collectAsState()


    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                title = { Text(text = "Movies", fontSize = 30.sp,color=Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                actions = {
                    IconButton(onClick = { contentViewModel.onEvent(ContentEvent.ShowDialog) }) {
                        Icon(imageVector = Icons.Outlined.Build, contentDescription = "Filter", tint = Color.White)
                    }
                }


            )

        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(NavigationConstant.PROFILE_SCREEN_ROUTE_KEY) }, shape = CircleShape) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Filter")
            }
        }
    ) {

        if(state.isSortTypeShowing){
        ContentMovieTypeDialog(onEvent = contentViewModel::onEvent, state = state)
        }
        LazyColumn(modifier = Modifier.padding(it).background(MaterialTheme.colorScheme.inverseOnSurface)) {

            items(state.movies){movie->

                MovieItem(movie = movie)

            }


        }
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController())
}