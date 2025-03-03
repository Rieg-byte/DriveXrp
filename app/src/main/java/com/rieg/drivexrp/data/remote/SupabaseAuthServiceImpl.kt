package com.rieg.drivexrp.data.remote

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.builtin.Email
import javax.inject.Inject

class SupabaseAuthServiceImpl @Inject constructor(
    private val supabaseAuth: Auth
) : SupabaseAuthService {
    override suspend fun signInWithEmail(userEmail: String, userPassword: String): Boolean {
        return try {
            supabaseAuth.signInWith(Email) {
                email = userEmail
                password = userPassword
            }
            true
        }
        catch (e: Exception) {
            false
        }
    }
}