package com.alberto.venture_prueba.navigation

sealed class NavigationRoute(val route: String) {
    object Login: NavigationRoute("login")
    object Home: NavigationRoute("home")
}