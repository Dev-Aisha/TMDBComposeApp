package com.example.tmdbccomposeapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.tmdbccomposeapp.presentation.navigation.NavGraph
import com.example.tmdbccomposeapp.presentation.screens.Popular.HomeScreen
import com.example.tmdbccomposeapp.presentation.screens.Popular.PopularMoviesViewModel
import com.example.tmdbccomposeapp.presentation.screens.onBoardingScreen.OnBoardingViewModel
import com.example.tmdbccomposeapp.presentation.screens.onBoardingScreen.OnboardingScreen
import com.example.tmdbccomposeapp.ui.theme.TMDBCcomposeAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMDBCcomposeAppTheme {
                NavGraph()

            }
        }
    }
}


