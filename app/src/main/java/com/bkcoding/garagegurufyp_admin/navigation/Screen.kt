package com.bkcoding.garagegurufyp_admin.navigation

sealed class Screen(val route: String) {
    data object LoginScreen : Screen("LoginScreen")
    data object HomeScreen : Screen("HomeScreen")
}