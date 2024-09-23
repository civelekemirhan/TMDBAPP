package com.example.tmdbapp.navigation

sealed class Screen(val route:String) {
    object Splash:Screen(NavigationConstant.SPLASH_SCREEN_ROUTE_KEY)
    object OnBoarding:Screen(NavigationConstant.ON_BOARDING_SCREEN_ROUTE_KEY)
    object Login:Screen(NavigationConstant.LOGIN_SCREEN_ROUTE_KEY)
    object Register:Screen(NavigationConstant.REGISTER_SCREEN_ROUTE_KEY)
    object ForgotPass:Screen(NavigationConstant.FORGOT_PASS_SCREEN_ROUTE_KEY)
    object Home:Screen(NavigationConstant.HOME_SCREEN_ROUTE_KEY)
    object Saves:Screen(NavigationConstant.SAVES_SCREEN_ROUTE_KEY)
    object Profile:Screen(NavigationConstant.PROFILE_SCREEN_ROUTE_KEY)
}