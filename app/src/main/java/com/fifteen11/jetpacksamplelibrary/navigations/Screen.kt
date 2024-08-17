package com.fifteen11.jetpacksamplelibrary.navigations

sealed class Screen(
    val route: String,
    val title: String
) {
    data object SplashScreen : Screen(route = "Splash", title = "Splash")
    data object LoginScreen : Screen(route = "LoginScreen", title = "Login")
    data object HomeScreen : Screen(route = "HomeScreen", title = "Home")
    data object DetailsScreen : Screen(route = "DetailsScreen", title = "Details")
    data object UserLoginHistoryScreen : Screen(route = "Login_History_Screen", title = "Login History")
}