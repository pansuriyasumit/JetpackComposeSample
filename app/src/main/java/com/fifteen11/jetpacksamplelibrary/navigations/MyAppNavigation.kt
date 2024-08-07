package com.fifteen11.jetpacksamplelibrary.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fifteen11.jetpacksamplelibrary.screens.DetailsScreen
import com.fifteen11.jetpacksamplelibrary.screens.HomeScreen

@Composable
fun MyAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        // Define your navigation graph here
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }

        composable(Screen.DetailsScreen.route) {
            DetailsScreen(navController)
        }
    }
}