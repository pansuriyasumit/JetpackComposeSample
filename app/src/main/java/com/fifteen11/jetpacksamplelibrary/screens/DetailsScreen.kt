package com.fifteen11.jetpacksamplelibrary.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fifteen11.jetpacksamplelibrary.data.model.ProblemsItem
import com.fifteen11.jetpacksamplelibrary.navigations.Screen
import com.fifteen11.jetpacksamplelibrary.screens.component.AppBar
import com.fifteen11.jetpacksamplelibrary.screens.component.MedicineCard

@Composable
fun DetailScreen(
    problemItem: ProblemsItem?
) {
    Scaffold(
        topBar = {
            AppBar("${Screen.DetailsScreen.title} - ${problemItem?.type}")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            problemItem?.let {
                MedicineCard(medicine = it)
            }
        }
    }
}