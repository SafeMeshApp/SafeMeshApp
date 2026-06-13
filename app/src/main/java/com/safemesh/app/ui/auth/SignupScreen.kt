package com.safemesh.app.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignupScreen(
    onSignupClick: (
        String,
        String,
        String,
        (String) -> Unit
    ) -> Unit,
    onLoginClick: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Create Account",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
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
                    name.isBlank() -> {
                        error = "Name cannot be empty"
                    }

                    email.isBlank() -> {
                        error = "Email cannot be empty"
                    }

                    !android.util.Patterns.EMAIL_ADDRESS
                        .matcher(email)
                        .matches() -> {
                        error = "Invalid email"
                    }

                    password.length < 6 -> {
                        error = "Password must be at least 6 characters"
                    }

                    else -> {

                        error = ""

                        onSignupClick(
                            name,
                            email,
                            password
                        ) { firebaseError ->

                            error = when {

                                firebaseError.contains("already", true) ->
                                    "Email already registered"

                                firebaseError.contains("email", true) ->
                                    "Invalid email"

                                firebaseError.contains("password", true) ->
                                    "Weak password"

                                else ->
                                    firebaseError
                            }
                        }
                    }
                }
            }
        ) {
            Text("Create Account")
        }

        TextButton(
            onClick = onLoginClick
        ) {
            Text("Already have an account? Login")
        }
    }
}
