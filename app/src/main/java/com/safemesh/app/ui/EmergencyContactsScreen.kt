package com.safemesh.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp

import com.safemesh.app.data.ContactRepository
import com.safemesh.app.model.EmergencyContact


@Composable
fun EmergencyContactsScreen(
    onAddContactClick: () -> Unit,
    onEditContactClick: (EmergencyContact) -> Unit

) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "Emergency Contacts"
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (ContactRepository.contacts.isEmpty()) {

            Text("No contacts added yet")

        } else {

            LazyColumn {

                items(ContactRepository.contacts) { contact ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text("Name: ${contact.name}")

                            Text("Phone: ${contact.phone}")

                            Text("Relationship: ${contact.relationship}")
                        }

                        Row {

                            Button(
                                onClick = {
                                    onEditContactClick(contact)
                                }
                            ) {
                                Text("Edit")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    ContactRepository.deleteContact(contact)
                                }
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onAddContactClick
        ) {
            Text("Add Contact")
        }
    }
}