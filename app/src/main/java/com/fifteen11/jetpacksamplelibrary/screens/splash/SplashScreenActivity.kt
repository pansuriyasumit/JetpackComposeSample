package com.fifteen11.jetpacksamplelibrary.screens.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.fifteen11.jetpacksamplelibrary.navigations.MyAppNavigation
import com.fifteen11.jetpacksamplelibrary.ui.theme.JetpackSampleLibraryTheme
import com.fifteen11.jetpacksamplelibrary.viewmodel.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreeActivity : ComponentActivity() {

    private val splashScreenViewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        enableEdgeToEdge()
        setupSplashScreen(splashScreen)

        setContent {
            val isSplashLoading by splashScreenViewModel.isSplashLoading.collectAsState()
            if (isSplashLoading) {
                // Show a simple composable while loading
                SplashScreenContent()
            } else {
                // Dismiss the splash screen and show the main content
                splashScreen.setKeepOnScreenCondition { false }
                NavigationStack {
                    MyAppNavigation()
                }
            }
            NavigationStack {
                MyAppNavigation()
            }
        }
    }

    private fun setupSplashScreen(splashScreen: SplashScreen) {
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            splashScreenViewProvider.view.animate()
                .alpha(0f)
                .setDuration(500L)
                .withEndAction {
                    splashScreenViewProvider.remove()
                }.start()
        }
    }
}

@Composable
fun SplashScreenContent() {
    // Simple composable to display during splash screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Splash Screen",
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 24.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold
            )
        )
        CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun NavigationStack(content: @Composable () -> Unit) {
    JetpackSampleLibraryTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFD0BCFF,
    showSystemUi = true,
    name = "Splash",
    locale = "en",
    uiMode = 0x1
)
@Composable
fun SplashScreenPreview() {
    SplashScreenContent()
}