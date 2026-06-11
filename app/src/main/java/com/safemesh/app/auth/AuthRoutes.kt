package com.safemesh.app.auth

sealed class AuthRoutes(val route: String) {
    object Login : AuthRoutes("login")
    object Signup : AuthRoutes("signup")
}
