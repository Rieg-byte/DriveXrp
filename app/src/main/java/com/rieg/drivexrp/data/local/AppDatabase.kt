package com.rieg.drivexrp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rieg.drivexrp.data.local.entities.CarEntity

@Database(entities = [CarEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun carDao(): CarDao
}