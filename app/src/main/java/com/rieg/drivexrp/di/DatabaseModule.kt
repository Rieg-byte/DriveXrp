package com.rieg.drivexrp.di

import android.content.Context
import androidx.room.Room
import com.rieg.drivexrp.data.local.AppDatabase
import com.rieg.drivexrp.data.local.CarDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCarDao(appDatabase: AppDatabase): CarDao {
        return appDatabase.carDao()
    }
}