package com.example.tmdbccomposeapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screens(val route: String){
    data object Onboarding : Screens("OnBoarding")
    data object Home : Screens("Home")

    data object Profile : Screens("Profile")
    data object Search : Screens("Search")


}


data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""

){
    fun bottomNavigationItem(): List<BottomNavigationItem>{
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
                label = "Search",
                icon = Icons.Filled.Search,
                route = Screens.Search.route
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = Icons.Filled.AccountCircle,
                route = Screens.Profile.route
            )
        )
    }
}