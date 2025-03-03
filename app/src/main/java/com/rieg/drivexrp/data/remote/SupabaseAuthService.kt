package com.rieg.drivexrp.data.remote

interface SupabaseAuthService {
    suspend fun signInWithEmail(userEmail: String, userPassword: String): Boolean
}