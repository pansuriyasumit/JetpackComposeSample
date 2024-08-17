package com.fifteen11.jetpacksamplelibrary.screens.main

import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityBackup : ComponentActivity() {

}

    /*private val splashScreenViewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        enableEdgeToEdge()

        setContent {
            val isSplashLoading by splashScreenViewModel.isSplashLoading.collectAsState()

            setupSplashScreen(splashScreen)

            LaunchedEffect(key1 = Unit) {
                delay(500L)
                splashScreen.setKeepOnScreenCondition { isSplashLoading }
            }

            AppContent {
                if (isSplashLoading) {
                    SplashScreenContent()
                } else {
                    MyAppNavigation()
                }
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
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun AppContent(content: @Composable () -> Unit) {
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
    name = "Splash", locale = "en"
)

@Composable
fun SplashScreenPreview() {
    SplashScreenContent()
}*/