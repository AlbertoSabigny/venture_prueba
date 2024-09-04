package com.alberto.venture_prueba.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alberto.venture_prueba.auth.presentation.LoginScreen
import com.alberto.venture_prueba.home.presentation.EmployeeScreen

@Composable
fun NavigationHost (
    navHostController: NavHostController,
    startDestination: NavigationRoute
){
    NavHost(navController = navHostController, startDestination = startDestination.route){
        composable(NavigationRoute.Login.route){
            LoginScreen (onLogin = {
                navHostController.popBackStack()
                navHostController.navigate(NavigationRoute.Home.route)
            }
            )
        }
        composable(NavigationRoute.Home.route){
            EmployeeScreen()
        }
    }
}