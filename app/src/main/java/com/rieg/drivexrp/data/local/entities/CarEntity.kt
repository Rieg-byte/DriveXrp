package com.rieg.drivexrp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "manufacturer")
    val manufacturer: String,
    @ColumnInfo(name = "price_per_day")
    val pricePerDay: Int,
    @ColumnInfo(name = "transmission")
    val transmission: String,
    @ColumnInfo(name = "fuel_type")
    val fuelType: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)
