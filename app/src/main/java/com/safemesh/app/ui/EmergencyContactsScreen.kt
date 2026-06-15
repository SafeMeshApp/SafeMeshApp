package com.safemesh.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.safemesh.app.data.FirestoreContactRepository
import com.safemesh.app.model.EmergencyContact


@Composable
fun EmergencyContactsScreen(
    onAddContactClick: () -> Unit,
    onEditContactClick: (EmergencyContact) -> Unit

) {
    var contacts by remember {
        mutableStateOf<List<EmergencyContact>>(emptyList())
    }

    LaunchedEffect(Unit) {

        FirestoreContactRepository.getContacts(

            onSuccess = { fetchedContacts ->
                contacts = fetchedContacts
            },

            onFailure = { error ->
                println(error)
            }
        )
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "Emergency Contacts"
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (contacts.isEmpty()) {

            Text("No contacts added yet")

        } else {

            LazyColumn {

                items(contacts) { contact ->

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
                                    FirestoreContactRepository.deleteContact(

                                        contactId = contact.id,

                                        onSuccess = {

                                            FirestoreContactRepository.getContacts(
                                                onSuccess = { fetchedContacts ->
                                                    contacts = fetchedContacts
                                                },
                                                onFailure = {error->
                                                    println(error)
                                                }
                                            )
                                        },

                                        onFailure = {
                                            println(it)
                                        }
                                    )
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