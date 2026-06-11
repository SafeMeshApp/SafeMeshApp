package com.safemesh.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.safemesh.app.auth.AuthRoutes
import com.safemesh.app.ui.auth.LoginScreen
import com.safemesh.app.ui.auth.SignupScreen

@Composable
fun AuthNavigation() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = AuthRoutes.Login.route
    ) {

        composable(AuthRoutes.Login.route) {

            LoginScreen(

                onLoginClick = { email, password ->

                    Toast.makeText(
                        context,
                        "Login: $email",
                        Toast.LENGTH_SHORT
                    ).show()
                },

                onSignupClick = {
                    navController.navigate(AuthRoutes.Signup.route)
                }
            )
        }

        composable(AuthRoutes.Signup.route) {

            SignupScreen(

                onSignupClick = { name, email, password ->

                    Toast.makeText(
                        context,
                        "Account Created",
                        Toast.LENGTH_SHORT
                    ).show()
                },

                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}