package com.rieg.drivexrp.di

import com.rieg.drivexrp.data.repository.CarRepositoryImpl
import com.rieg.drivexrp.domain.repository.CarRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CarModule {

    @Binds
    abstract fun provideCarRepository(
        carRepository: CarRepositoryImpl
    ): CarRepository

}