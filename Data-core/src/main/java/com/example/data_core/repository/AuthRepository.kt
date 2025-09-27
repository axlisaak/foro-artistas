package com.example.data_core.repository

import com.example.data_core.firebase.FirebaseFunService
import com.example.data_core.model.User

class AuthRepository {

    private val firebaseService = FirebaseFunService

    suspend fun register(email: String, pass: String, name: String, role: String): Result<Unit> {
        return try {
            firebaseService.registerUser(email, pass, name, role)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, pass: String): Result<User> {
        return try {
            val uid = firebaseService.signInWithEmail(email, pass)
            val userProfile = firebaseService.getUserProfile(uid)
                ?: throw Exception("No se encontró el perfil del usuario.")
            Result.success(userProfile)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginWithGoogle(idToken: String, displayName: String, email: String): Result<User> {
        return try {
            firebaseService.signInWithGoogle(idToken, displayName, email)
            val uid = firebaseService.getCurrentUserId()!!
            val userProfile = firebaseService.getUserProfile(uid)!!
            Result.success(userProfile)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        firebaseService.logout()
    }
}