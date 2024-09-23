package com.example.tmdbapp.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.tmdbapp.feature.content.ui.screen.DetailsScreen
import com.example.tmdbapp.feature.content.ui.screen.HomeScreen
import com.example.tmdbapp.feature.content.ui.screen.SavesScreen

fun NavGraphBuilder.HomeNavGraph(navController: NavHostController) {


    navigation(
        startDestination = Screen.Home.route,
        route = NavigationConstant.CONTENT_NAV_GRAPH_ROUTE_KEY
    ) {

        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Saves.route) {
            SavesScreen(navController)
        }
        composable(route = "${Screen.Details.route}/{title}/{overview}/{poster_path}/{vote_average}",
            arguments = listOf(
                navArgument("title"){
                    type= NavType.StringType
                },
                navArgument("overview"){
                    type= NavType.StringType
                },
                navArgument("poster_path"){
                    type= NavType.StringType
                },
                navArgument("vote_average"){
                    type= NavType.FloatType
                }
            )) {
            val title = Uri.decode(it.arguments?.getString("title") ?: "")
            val overview = Uri.decode(it.arguments?.getString("overview") ?: "")
            val poster_path = Uri.decode(it.arguments?.getString("poster_path") ?: "")
            val vote_average = it.arguments?.getFloat("vote_average") ?: 0f
            DetailsScreen(navController, title =title, overview = overview, poster_path = poster_path, vote_average = vote_average)
        }

    }

}