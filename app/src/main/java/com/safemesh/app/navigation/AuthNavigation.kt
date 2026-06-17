package com.safemesh.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.safemesh.app.auth.AuthRoutes
import com.safemesh.app.auth.FirebaseAuthManager
import com.safemesh.app.data.ProfileRepository
import com.safemesh.app.model.UserProfile
import com.safemesh.app.ui.auth.LoginScreen
import com.safemesh.app.ui.auth.SignupScreen

@Composable
fun AuthNavigation(
    isDarkMode: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    val authManager = FirebaseAuthManager()

    val startDestination =
        if (authManager.currentUser() != null)
            "app"
        else
            AuthRoutes.Login.route
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(AuthRoutes.Login.route) {

            LoginScreen(

                onLoginClick = { email, password, onError ->

                    authManager.login(
                        email,
                        password
                    ) { success, error ->

                        if (success) {

                            navController.navigate("app") {
                                popUpTo(AuthRoutes.Login.route) {
                                    inclusive = true
                                }
                            }

                        } else {

                            onError(
                                error ?: "Login failed"
                            )
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

                onSignupClick = { name, phoneNumber, email, password, onError ->
                    authManager.signup(
                        name,
                        phoneNumber,
                        email,
                        password
                    ) { success, error ->

                        if (success) {

                            val currentUser = authManager.currentUser()

                            currentUser?.let { user ->

                                ProfileRepository.saveProfile(

                                    UserProfile(
                                        uid = user.uid,
                                        email = user.email ?: "",
                                        name = name,
                                        phone = phoneNumber
                                    ),

                                    onSuccess = {
                                        println("Profile saved")
                                    },

                                    onFailure = {
                                        println(it)
                                    }
                                )
                            }

                            navController.navigate("app") {
                                popUpTo(AuthRoutes.Signup.route) {
                                    inclusive = true
                                }
                            }

                        } else {

                            onError(error ?: "Signup failed")
                        }
                    }
                },
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("app") {

            AppNavigation(

                onLogout = {

                    authManager.logout()
                    navController.navigate(AuthRoutes.Login.route) {
                        popUpTo("app") {
                            inclusive = true
                        }
                    }
                },
                isDarkMode = isDarkMode,
                onThemeChange = onThemeChange
            )
        }
    }
}