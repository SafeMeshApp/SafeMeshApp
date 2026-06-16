package com.safemesh.app.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseAuthManager {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun signup(
        name: String,
        phoneNumber: String,
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val uid = auth.currentUser?.uid ?: ""

                    val userData = hashMapOf(
                        "uid" to uid,
                        "name" to name,
                        "phoneNumber" to phoneNumber,
                        "email" to email
                    )

                    firestore.collection("users")
                        .document(uid)
                        .set(userData)
                        .addOnSuccessListener {
                            callback(true, null)
                        }
                        .addOnFailureListener {
                            callback(false, it.message)
                        }

                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    fun login(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    fun logout() {
        auth.signOut()
    }

    fun currentUser(): FirebaseUser? {
        return auth.currentUser
    }
}