
package com.safemesh.app.data

data class EmergencyMessage(
    val messageId: String,
    val userId: String,
    val timestamp: Long,
    val latitude: Double,
    val longitude: Double,
    val emergencyType: String,
    val priority: String = "CRITICAL",
    val ttl: Int = 10
)