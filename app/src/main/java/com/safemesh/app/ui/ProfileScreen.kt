package com.safemesh.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import com.safemesh.app.model.UserProfile
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.safemesh.app.auth.FirebaseAuthManager
import com.safemesh.app.data.ProfileRepository

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    onEditClick: () -> Unit
) {

    var profile by remember {
        mutableStateOf<UserProfile?>(null)
    }

    LaunchedEffect(Unit) {

        ProfileRepository.getProfile(
            uid = FirebaseAuthManager()
                .currentUser()!!
                .uid,

            onSuccess = { fetchedProfile ->
                profile = fetchedProfile
            },

            onFailure = { error ->
                println(error)
            }
        )
    }

    if (profile == null) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Loading...")
        }

        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Name: ${profile!!.name}")

        Spacer(modifier = Modifier.height(8.dp))

        Text("Email: ${profile!!.email}")

        Spacer(modifier = Modifier.height(8.dp))

        Text("Phone: ${profile!!.phone}")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onEditClick
        ) {
            Text("Edit Profile")
        }
    }
}