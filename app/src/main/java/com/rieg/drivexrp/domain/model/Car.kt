package com.rieg.drivexrp.domain.model

data class Car(
    val name: String,
    val manufacturer: String,
    val pricePerDay: Int,
    val transmission: String,
    val fuelType: String,
    val imageUrl: String
)
