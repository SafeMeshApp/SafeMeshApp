package com.safemesh.app.data

import androidx.compose.runtime.mutableStateListOf
import com.safemesh.app.model.EmergencyContact

object ContactRepository {

    val contacts = mutableStateListOf<EmergencyContact>()
    var selectedContact: EmergencyContact? = null


    fun addContact(contact: EmergencyContact) {
        contacts.add(contact)
    }

    fun deleteContact(contact: EmergencyContact) {
        contacts.remove(contact)
    }

    fun updateContact(updated: EmergencyContact) {
        val index = contacts.indexOfFirst {
            it.id == updated.id
        }

        if (index != -1) {
            contacts[index] = updated
        }
    }
}