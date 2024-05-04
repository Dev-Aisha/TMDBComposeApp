package com.example.tmdbccomposeapp.presentation.navigation


sealed class Screens(val route: String){
    data object Onboarding : Screens("OnBoarding")
    data object Home : Screens("Home")
}