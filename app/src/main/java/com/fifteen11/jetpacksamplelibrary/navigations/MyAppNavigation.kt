package com.fifteen11.jetpacksamplelibrary.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fifteen11.jetpacksamplelibrary.data.model.ProblemsItem
import com.fifteen11.jetpacksamplelibrary.screens.DetailScreen
import com.fifteen11.jetpacksamplelibrary.screens.HomeScreen
import com.fifteen11.jetpacksamplelibrary.screens.LoginScreen
import com.fifteen11.jetpacksamplelibrary.screens.SplashScreen
import com.fifteen11.jetpacksamplelibrary.screens.UserLoginHistory
import com.fifteen11.jetpacksamplelibrary.ui.theme.FadeIn
import com.fifteen11.jetpacksamplelibrary.ui.theme.FadeOut
import com.google.gson.Gson

@Composable
fun MyAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        enterTransition = { FadeIn },
        exitTransition = { FadeOut },
        popEnterTransition = { FadeIn },
        popExitTransition = { FadeOut },
    ) {
        // Define your navigation graph here.

        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onLogin = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = "${Screen.DetailsScreen.route}?problemItem={problemItem}",
            arguments = listOf(
                navArgument(name = "problemItem") {
                    type = NavType.StringType
                    defaultValue = ""
                },
            )
        ) {
            val problemItemJson = it.arguments?.getString("problemItem")
            val problemItem = Gson().fromJson(problemItemJson, ProblemsItem::class.java)
            DetailScreen(problemItem = problemItem)
        }

        composable(route = Screen.UserLoginHistoryScreen.route) {
            UserLoginHistory()
        }
    }
}