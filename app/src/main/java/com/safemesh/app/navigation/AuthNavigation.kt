package com.safemesh.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.safemesh.app.auth.AuthRoutes
import com.safemesh.app.ui.auth.LoginScreen
import com.safemesh.app.ui.auth.SignupScreen

@Composable
fun AuthNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthRoutes.Login.route
    ) {

        composable(AuthRoutes.Login.route) {

            LoginScreen(

                onLoginClick = { email, password ->

                    navController.navigate("app") {
                        popUpTo(AuthRoutes.Login.route) {
                            inclusive = true
                        }
                    }
                },

                onSignupClick = {
                    navController.navigate(AuthRoutes.Signup.route)
                }
            )
        }

        composable(AuthRoutes.Signup.route) {

            SignupScreen(

                onSignupClick = { name, email, password ->

                    navController.navigate("app") {
                        popUpTo(AuthRoutes.Signup.route) {
                            inclusive = true
                        }
                    }
                },

                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("app") {
            AppNavigation()
        }
    }
}