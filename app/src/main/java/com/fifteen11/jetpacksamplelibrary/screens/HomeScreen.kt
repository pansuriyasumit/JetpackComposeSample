package com.fifteen11.jetpacksamplelibrary.screens

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.PowerSettingsNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fifteen11.jetpacksamplelibrary.R
import com.fifteen11.jetpacksamplelibrary.navigations.Screen
import com.fifteen11.jetpacksamplelibrary.screens.component.AppBar
import com.fifteen11.jetpacksamplelibrary.screens.component.LockScreenOrientation
import com.fifteen11.jetpacksamplelibrary.screens.component.MedicineList
import com.fifteen11.jetpacksamplelibrary.ui.theme.SecondaryColor
import com.fifteen11.jetpacksamplelibrary.utils.BackPressHandler
import com.fifteen11.jetpacksamplelibrary.viewmodel.HomeViewModel
import com.google.gson.Gson

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val uiState by homeViewModel.medicineUiState.collectAsState()
    val (medicines, isLoading, error) = uiState
    val medicineData = medicines?.problems
    val userName by homeViewModel.userName.collectAsState()
    val greetingMessage by homeViewModel.greetingMessage.collectAsState()

    LaunchedEffect(Unit) {
        if (!homeViewModel.isMedicinesLoaded) {
            homeViewModel.fetchMedicines()
            homeViewModel.isMedicinesLoaded = true
        }
    }

    BackPressHandler()

    Scaffold(
        topBar = {
            AppBar(Screen.HomeScreen.title, isShowIcon = true, onClick = {
                navController.navigate(Screen.UserLoginHistoryScreen.route)
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                homeViewModel.clearUserData()
                navController.navigate(route = Screen.LoginScreen.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }) {
                Icon(imageVector = Icons.TwoTone.PowerSettingsNew, contentDescription = "Logout")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GreetingText(
                "$greetingMessage, $userName!", color = SecondaryColor
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                LoadingView()
            } else if (error != null) {
                ErrorText(error)
            } else {
                MedicineList(medicine = medicineData ?: listOf()) { problemId ->
                    val problemItem = medicineData?.first { it.id == problemId }
                    problemItem?.let {
                        val problemItemJson = Gson().toJson(it)
                        navController.navigate(
                            "${Screen.DetailsScreen.route}?problemItem=${problemItemJson}"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingText(text: String, color: Color) {
    Text(
        text = text,
        fontFamily = FontFamily(Font(R.font.lato_bold)),
        letterSpacing = 1.sp,
        fontSize = 18.sp,
        color = color,
        style = MaterialTheme.typography.labelLarge
    )
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(44.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@Composable
fun ErrorText(text: String) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.lato_bold)),
            letterSpacing = 1.sp,
            fontSize = 18.sp,
            color = SecondaryColor,
            style = MaterialTheme.typography.labelLarge
        )
    }
}