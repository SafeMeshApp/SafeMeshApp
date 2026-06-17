package com.safemesh.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme


@Composable
fun HomeScreen(
    onSosClick: () -> Unit,
    onCheckInClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onContactsClick: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "SafeMesh Home",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onSosClick,
            modifier = Modifier.fillMaxWidth()) {
            Text("Send SOS")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onCheckInClick,
            modifier = Modifier.fillMaxWidth()) {
            Text("Check In")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onSettingsClick,
            modifier = Modifier.fillMaxWidth()) {
            Text("Settings")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onContactsClick,
            modifier = Modifier.fillMaxWidth()) {
            Text("Emergency Contacts")
        }
    }
}