package org.arba.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.arba.presentation.navigation.route.ObjectRoute
import org.arba.presentation.ui.screen.HomeScreen
import org.arba.presentation.ui.screen.SettingScreen

@Composable
fun HostNavRoute() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ObjectRoute.HomeScreen.route) {
        composable(ObjectRoute.HomeScreen.route) { HomeScreen(navController) }
        composable(ObjectRoute.SettingScreen.route) { SettingScreen(navController) }
    }
}