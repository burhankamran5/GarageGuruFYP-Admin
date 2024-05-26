package com.bkcoding.garagegurufyp_admin.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bkcoding.garagegurufyp_admin.ui.home.HomeScreen
import com.bkcoding.garagegurufyp_admin.ui.login.LoginScreen

@Composable
fun GarageGuruNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(Screen.LoginScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            LoginScreen(navController)
        }

        composable(Screen.HomeScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }) {
            HomeScreen(navController)
        }
    }
}