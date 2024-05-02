package com.example.tmdbccomposeapp.presentation.navigation

sealed class Screens : Destination {
    object Onboarding : Screens() {
        override val route: String = "OnBoarding"
    }

    object Home : Screens() {
        override val route: String = "Home"
    }
}

interface Destination {
    val route: String
}