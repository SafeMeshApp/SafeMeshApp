package com.safemesh.app.auth

class AuthRepository(
    private val firebaseAuthManager: FirebaseAuthManager
) {
    fun signup(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {
        firebaseAuthManager.signup(email, password, callback)
    }

    fun login(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {
        firebaseAuthManager.login(email, password, callback)
    }

    fun logout() {
        firebaseAuthManager.logout()
    }

    fun isUserLoggedIn(): Boolean {
        return firebaseAuthManager.currentUser() != null
    }
}