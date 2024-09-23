package com.example.tmdbapp.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tmdbapp.OnBoarding
import com.example.tmdbapp.feature.auth.ui.Screen.LoginScreen
import com.example.tmdbapp.feature.auth.ui.Screen.RegisterScreen
import com.example.tmdbapp.feature.auth.ui.Screen.SplashScreen
import com.example.tmdbapp.feature.auth.ui.Screen.ForgotPassScreen

fun NavGraphBuilder.AuthGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Splash.route,
        route = NavigationConstant.AUTH_NAV_GRAPH_ROUTE_KEY
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.OnBoarding.route) {
            OnBoarding(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.ForgotPass.route) {
            ForgotPassScreen(navController = navController)
        }
    }
}