package com.safemesh.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.safemesh.app.model.UserProfile

@Composable
fun EditProfileScreen(
    profile: UserProfile,
    onSave: (UserProfile) -> Unit,
    onCancel: () -> Unit
) {

    var name by remember {
        mutableStateOf(profile.name)
    }

    var phone by remember {
        mutableStateOf(profile.phone)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Edit Profile",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { newValue ->
                name = newValue
            },
            label = {
                Text("Name")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { newValue ->
                phone = newValue
            },
            label = {
                Text("Phone")
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                onSave(
                    profile.copy(
                        name = name,
                        phone = phone
                    )
                )
            }
        ) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onCancel
        ) {
            Text("Cancel")
        }
    }
}