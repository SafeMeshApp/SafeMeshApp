package com.safemesh.app.ui.auth

import android.util.Patterns
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
        String,
        (String) -> Unit
    ) -> Unit,
    onLoginClick: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
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

        // Name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Phone Number
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                if (it.length <= 10 && it.all { ch -> ch.isDigit() }) {
                    phoneNumber = it
                }
            },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Password
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

                    name.isBlank() -> {
                        error = "Name cannot be empty"
                    }

                    phoneNumber.length != 10 -> {
                        error = "Enter a valid 10-digit phone number"
                    }

                    email.isBlank() -> {
                        error = "Email cannot be empty"
                    }

                    !Patterns.EMAIL_ADDRESS
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
                            phoneNumber,
                            email,
                            password
                        ) { firebaseError ->

                            error = when {

                                firebaseError.contains(
                                    "already",
                                    true
                                ) -> "Email already registered"

                                firebaseError.contains(
                                    "email",
                                    true
                                ) -> "Invalid email"

                                firebaseError.contains(
                                    "password",
                                    true
                                ) -> "Weak password"

                                else -> firebaseError
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
