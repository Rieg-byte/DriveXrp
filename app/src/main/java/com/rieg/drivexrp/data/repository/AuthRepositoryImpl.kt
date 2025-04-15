package com.rieg.drivexrp.data.repository

import com.rieg.drivexrp.data.remote.SupabaseAuthService
import com.rieg.drivexrp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val supabaseAuthService: SupabaseAuthService
): AuthRepository {
    override suspend fun signInWithEmail(email: String, password: String): Boolean {
        return supabaseAuthService.signInWithEmail(email, password)
    }
}