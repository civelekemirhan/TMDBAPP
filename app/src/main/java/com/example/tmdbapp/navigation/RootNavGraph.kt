package com.example.tmdbapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavigationConstant.AUTH_NAV_GRAPH_ROUTE_KEY,
        route = NavigationConstant.ROOT_NAV_GRAPH_ROUTE_KEY
    ) {
        AuthGraph(navController)
        HomeNavGraph(navController)
    }

}