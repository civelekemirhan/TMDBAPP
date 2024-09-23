package com.example.tmdbapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tmdbapp.feature.content.ui.Screen.HomeScreen
import com.example.tmdbapp.feature.content.ui.Screen.ProfileScreen
import com.example.tmdbapp.feature.content.ui.Screen.SavesScreen

fun NavGraphBuilder.HomeNavGraph(navController: NavHostController) {


    navigation(
        startDestination = Screen.Home.route,
        route = NavigationConstant.CONTENT_NAV_GRAPH_ROUTE_KEY
    ) {

        composable(route=Screen.Home.route){
            HomeScreen(navController)
        }
        composable(route=Screen.Saves.route) {
            SavesScreen(navController)
        }
        composable(route=Screen.Profile.route) {
            ProfileScreen(navController)
        }

    }

}