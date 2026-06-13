

package com.safemesh.app.model


data class EmergencyContact(
    val id: Long = System.currentTimeMillis(),
    val name: String,
    val phone: String,
    val relationship: String
)