package com.safemesh.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.safemesh.app.model.EmergencyContact
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth

@Composable
fun EditContactScreen(
    contact: EmergencyContact,
    onUpdateContact: (EmergencyContact) -> Unit,
    onCancel: () -> Unit
) {

    var name by remember { mutableStateOf(contact.name) }
    var phone by remember { mutableStateOf(contact.phone) }
    var relationship by remember { mutableStateOf(contact.relationship) }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {

        Text("Edit Contact")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = relationship,
            onValueChange = { relationship = it },
            label = { Text("Relationship") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Row {


            Button(
                onClick = {

                    when {

                        name.isBlank() ->
                            error = "Name required"

                        !phone.matches(Regex("^[6-9]\\d{9}$")) ->
                            error = "Enter valid Indian mobile number"

                        relationship.isBlank() ->
                            error = "Relationship required"

                        else -> {

                            onUpdateContact(
                                contact.copy(
                                    name = name,
                                    phone = phone,
                                    relationship = relationship
                                )
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Changes")
            }

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedButton(
                onClick = {
                    onCancel()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancel")
            }
        }
        }
    }
