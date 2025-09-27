package com.example.data_core.model


data class User(
    val uid: String = "",
    val email: String = "",
    val displayName: String = "",
    val role: String = "USER" // Roles: "USER", "ARTIST", "ADMIN"
)