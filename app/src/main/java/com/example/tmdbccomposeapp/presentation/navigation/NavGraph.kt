package com.example.tmdbccomposeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tmdbccomposeapp.presentation.screens.DetailScreen.MovieDetailViewModel
import com.example.tmdbccomposeapp.presentation.screens.DetailScreen.MovieDetailsScreen
import com.example.tmdbccomposeapp.presentation.screens.Popular.HomeScreen
import com.example.tmdbccomposeapp.presentation.screens.Popular.PopularMoviesViewModel
import com.example.tmdbccomposeapp.presentation.screens.SearchScreen.SearchScreen
import com.example.tmdbccomposeapp.presentation.screens.SearchScreen.SearchScreenViewModel
import com.example.tmdbccomposeapp.presentation.screens.onBoardingScreen.OnBoardingViewModel
import com.example.tmdbccomposeapp.presentation.screens.onBoardingScreen.OnboardingScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()){
    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
    NavHost(navController = navController,
        startDestination = onBoardingViewModel.startDestination.orEmpty())
    {
        composable(route = Screens.Onboarding.route){
            OnboardingScreen(onBoardingViewModel, navController)
        }
        composable(Screens.Home.route){
            val viewModel = hiltViewModel<PopularMoviesViewModel>()
            HomeScreen(viewModel.popularMoviesState, navController = navController)

        }
        composable("${Screens.Detail.route}/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
            }
        )) {
            val viewModel = hiltViewModel<MovieDetailViewModel>()
            MovieDetailsScreen(viewModel=viewModel, int = it.arguments?.getInt("id"), )

        }

        composable(Screens.Search.route) {
            val viewModel = hiltViewModel<SearchScreenViewModel>()
            SearchScreen(navController = navController, searchScreenViewModel = viewModel)
        }
    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
        saveState = true
    }
}

