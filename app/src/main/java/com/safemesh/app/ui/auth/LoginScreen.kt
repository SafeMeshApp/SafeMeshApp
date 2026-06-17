package com.safemesh.app.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
@Composable
fun LoginScreen(
    onLoginClick: (
        String,
        String,
        (String) -> Unit
    ) -> Unit,
    onSignupClick: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ){

        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {

                when {
                    email.isBlank() -> {
                        error = "Email cannot be empty"
                    }

                    !android.util.Patterns.EMAIL_ADDRESS
                        .matcher(email)
                        .matches() -> {
                        error = "Invalid email"
                    }

                    password.isBlank() -> {
                        error = "Password cannot be empty"
                    }

                    else -> {

                        error = ""

                        onLoginClick(
                            email,
                            password
                        ) { firebaseError ->

                            error = when {
                                firebaseError.contains("password", true) ->
                                    "Incorrect password"

                                firebaseError.contains("user", true) ->
                                    "No account found"

                                firebaseError.contains("email", true) ->
                                    "Invalid email"

                                else ->
                                    firebaseError
                            }
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        TextButton(
            onClick = onSignupClick
        ) {
            Text("Don't have an account? Sign Up")
        }
    }
}