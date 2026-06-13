package com.safemesh.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onSosClick: () -> Unit,
    onCheckInClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onContactsClick: () -> Unit

) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("SafeMesh Home")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onSosClick) {
            Text("Send SOS")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = onCheckInClick) {
            Text("Check In")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = onSettingsClick) {
            Text("Settings")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onContactsClick) {
            Text("Emergency Contacts")
        }
    }
}