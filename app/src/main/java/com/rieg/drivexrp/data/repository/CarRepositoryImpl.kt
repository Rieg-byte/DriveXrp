package com.rieg.drivexrp.data.repository

import com.rieg.drivexrp.data.local.CarDao
import com.rieg.drivexrp.data.mappers.toCar
import com.rieg.drivexrp.domain.model.Car
import com.rieg.drivexrp.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val carDao: CarDao
): CarRepository {
    override suspend fun getAllCars(): List<Car> = carDao.getAll().map { it.toCar() }
    override suspend fun getCarsByQuery(query: String): List<Car> = carDao
        .getCarsByName(name = query)
        .map { it.toCar() }
}