package com.safemesh.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background


@Composable
fun SosScreen() {

    val emergencyTypes = listOf(
        "🩺 Medical",
        "🔥 Fire",
        "🌊 Flood",
        "⚠️ Accident",
        "👶 Missing Person",
        "🚨 General SOS"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Select Emergency Type",
            style = MaterialTheme.typography.headlineSmall
        )

        emergencyTypes.forEach { type ->
            Button(
                onClick = { println("Selected: $type") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(type)
            }
        }
    }
}