
package com.safemesh.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    userEmail: String,
    isDarkMode: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onLogout: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        HorizontalDivider()

        Text(
            text = "Profile",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = userEmail
        )

        HorizontalDivider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text("Dark Mode")

            Switch(
                checked = isDarkMode,
                onCheckedChange = {
                    onThemeChange(it)
                }
            )
        }

        HorizontalDivider()

        Button(
            onClick = onLogout,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text("Logout")
        }
    }
}