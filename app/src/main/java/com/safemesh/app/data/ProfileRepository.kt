package com.safemesh.app.data

import com.google.firebase.firestore.FirebaseFirestore
import com.safemesh.app.model.UserProfile

object ProfileRepository {

    private val db = FirebaseFirestore.getInstance()

    fun saveProfile(
        profile: UserProfile,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        db.collection("users")
            .document(profile.uid)
            .set(profile)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Failed")
            }
    }

    fun getProfile(
        uid: String,
        onSuccess: (UserProfile?) -> Unit,
        onFailure: (String) -> Unit
    ) {

        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->

                onSuccess(
                    document.toObject(UserProfile::class.java)
                )
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Failed")
            }
    }

    fun updateProfile(
        profile: UserProfile,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        db.collection("users")
            .document(profile.uid)
            .set(profile)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Failed")
            }
    }
}