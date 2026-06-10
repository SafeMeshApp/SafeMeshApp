package com.safemesh.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.safemesh.app.ui.CheckInScreen
import com.safemesh.app.ui.HomeScreen
import com.safemesh.app.ui.SettingsScreen
import com.safemesh.app.ui.SosScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        composable(Routes.HOME) {
            HomeScreen(
                onSosClick = {
                    navController.navigate(Routes.SOS)
                },
                onCheckInClick = {
                    navController.navigate(Routes.CHECKIN)
                },
                onSettingsClick = {
                    navController.navigate(Routes.SETTINGS)
                }
            )
        }

        composable(Routes.SOS) {
            SosScreen()
        }

        composable(Routes.CHECKIN) {
            CheckInScreen()
        }

        composable(Routes.SETTINGS) {
            SettingsScreen()
        }
    }
}