package com.fifteen11.jetpacksamplelibrary.navigations

sealed class Screen(val route: String, val title: String) {
    data object SplashScreen : Screen("SplashScreen", "Splash")
    data object HomeScreen : Screen("HomeScreen", "Home")
    data object DetailsScreen : Screen("DetailsScreen", "Details")
}