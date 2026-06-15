package com.safemesh.app.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.safemesh.app.model.EmergencyContact


object FirestoreContactRepository {

    private fun getContactsCollection() =
        db.collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection("contacts")
    private val db = FirebaseFirestore.getInstance()

    fun addContact(
        contact: EmergencyContact,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        val docRef = getContactsCollection().document()

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

        getContactsCollection()
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

        getContactsCollection()
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

        getContactsCollection()
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