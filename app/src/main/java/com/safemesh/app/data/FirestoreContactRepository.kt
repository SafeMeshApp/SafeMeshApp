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

        val docRef = db.collection("contacts").document()

        val contactWithId = contact.copy(
            id = docRef.id
        )

        docRef.set(contactWithId)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Failed")
            }
    }

    fun getContacts(
        onSuccess: (List<EmergencyContact>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        db.collection("contacts")
            .get()
            .addOnSuccessListener { result ->

                val contacts = result.documents.mapNotNull {
                    it.toObject(EmergencyContact::class.java)
                }

                onSuccess(contacts)
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Failed")
            }
    }

    fun deleteContact(
        contactId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        db.collection("contacts")
            .document(contactId)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Failed")
            }
    }

    fun updateContact(
        contact: EmergencyContact,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        db.collection("contacts")
            .document(contact.id)
            .set(contact)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Failed")
            }
    }
}