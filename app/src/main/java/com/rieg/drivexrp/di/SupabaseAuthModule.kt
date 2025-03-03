package com.rieg.drivexrp.di

import com.rieg.drivexrp.data.remote.SupabaseAuthService
import com.rieg.drivexrp.data.remote.SupabaseAuthServiceImpl
import com.rieg.drivexrp.data.repository.AuthRepositoryImpl
import com.rieg.drivexrp.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SupabaseAuthModule {

    @Binds
    abstract fun provideAuthService(
        supabaseAuthService: SupabaseAuthServiceImpl
    ): SupabaseAuthService

    @Binds
    abstract fun provideAuthRepository(
        authenticationRepository: AuthRepositoryImpl
    ): AuthRepository
}