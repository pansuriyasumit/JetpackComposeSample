package com.fifteen11.jetpacksamplelibrary.screens

import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fifteen11.jetpacksamplelibrary.data.local.UserLoginHistoryEntity
import com.fifteen11.jetpacksamplelibrary.navigations.Screen
import com.fifteen11.jetpacksamplelibrary.screens.component.AppBar
import com.fifteen11.jetpacksamplelibrary.screens.component.LockScreenOrientation
import com.fifteen11.jetpacksamplelibrary.utils.AppConstant.Companion.DATE_FORMAT
import com.fifteen11.jetpacksamplelibrary.utils.DateUtil.convertDateString
import com.fifteen11.jetpacksamplelibrary.viewmodel.UserHistoryViewModel

@Composable
fun UserLoginHistory(historyViewModel: UserHistoryViewModel = hiltViewModel()) {

    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    Scaffold(
        topBar = {
            AppBar(Screen.UserLoginHistoryScreen.title)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Content(historyViewModel)
        }
    }
}

@Composable
fun Content(viewModel: UserHistoryViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is UserHistoryViewModel.UserHistoryUiState.Loading -> {
            // Show loading indicator

            CircularProgressIndicator()
        }

        is UserHistoryViewModel.UserHistoryUiState.Success -> {
            val history = (uiState as UserHistoryViewModel.UserHistoryUiState.Success).history
            val mod = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .height(80.dp)

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(history) { historyItem ->
                        val item = ImmutableLoginHistory(historyItem)
                        HistoryCard(wrapper = item, modifier = mod)
                    }
                }
            )
        }

        is UserHistoryViewModel.UserHistoryUiState.Error -> {
            Text(text = "Error: ${(uiState as UserHistoryViewModel.UserHistoryUiState.Error).message}")
        }
    }
}

@Immutable
data class ImmutableLoginHistory(val userLoginHistoryEntity: UserLoginHistoryEntity)

@Composable
fun HistoryCard(
    wrapper: ImmutableLoginHistory,
    modifier: Modifier
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = wrapper.userLoginHistoryEntity.userName,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = convertDateString(
                        format = DATE_FORMAT,
                        dateString = wrapper.userLoginHistoryEntity.createdAt.toString()
                    ),
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}