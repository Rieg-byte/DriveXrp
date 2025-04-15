package com.rieg.drivexrp.presentation.home

import com.rieg.drivexrp.domain.model.Car

sealed interface HomeUiState {
    data class Success(
        val listOfCars: List<Car> = emptyList()
    ): HomeUiState
    object Loading: HomeUiState
    object Error: HomeUiState
    data class NotFound(
        val query: String
    ): HomeUiState
}