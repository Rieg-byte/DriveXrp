package com.rieg.drivexrp.domain.repository

interface AuthRepository {
    suspend fun signInWithEmail(email: String, password: String): Boolean
}