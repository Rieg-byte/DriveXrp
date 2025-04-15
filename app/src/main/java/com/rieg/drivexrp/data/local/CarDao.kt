package com.rieg.drivexrp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rieg.drivexrp.data.local.entities.CarEntity

@Dao
interface CarDao {
    @Insert
    suspend fun insertAll(vararg car: CarEntity)

    @Insert
    suspend fun insert(car: CarEntity)

    @Delete
    suspend fun delete(car: CarEntity)

    @Query("SELECT * FROM cars")
    suspend fun getAll(): List<CarEntity>

    @Query("SELECT * FROM cars WHERE LOWER(name) LIKE LOWER(:name || '%')")
    suspend fun getCarsByName(name: String): List<CarEntity>
}