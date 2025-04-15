package com.rieg.drivexrp.data.mappers

import com.rieg.drivexrp.data.local.entities.CarEntity
import com.rieg.drivexrp.domain.model.Car

fun CarEntity.toCar(): Car {
    return Car(
        name = name,
        manufacturer = manufacturer,
        pricePerDay = pricePerDay,
        transmission = transmission,
        fuelType = fuelType,
        imageUrl = imageUrl
    )
}