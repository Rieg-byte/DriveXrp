package com.rieg.drivexrp.di

import com.rieg.drivexrp.data.repository.SettingsRepositoryImpl
import com.rieg.drivexrp.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsRepositoryModule {
    @Binds
    abstract fun provideSettingRepository(settingsRepository: SettingsRepositoryImpl): SettingsRepository
}