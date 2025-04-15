package com.rieg.drivexrp.domain.repository

import com.rieg.drivexrp.domain.model.Car

interface CarRepository {
    suspend fun getAllCars(): List<Car>
    suspend fun getCarsByQuery(query: String): List<Car>
}