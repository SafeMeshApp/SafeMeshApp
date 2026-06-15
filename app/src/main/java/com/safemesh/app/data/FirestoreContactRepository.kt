package com.safemesh.app.data

import com.google.firebase.firestore.FirebaseFirestore
import com.safemesh.app.model.EmergencyContact

object FirestoreContactRepository {

    private val db = FirebaseFirestore.getInstance()

    fun addContact(
        contact: EmergencyContact,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        db.collection("contacts")
            .add(contact)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Failed")
            }
    }
}