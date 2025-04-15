package com.rieg.drivexrp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rieg.drivexrp.domain.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val carRepository: CarRepository
): ViewModel() {
    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        getCarList()
    }
    var userInput: String by mutableStateOf("")
        private set

    /**
     * Обновление пользовательского ввода
     */
    fun updateUserInput(newUserInput: String) {
        userInput = newUserInput
    }

    fun getCarList(query: String = "") {
        viewModelScope.launch {
            try {
                _homeUiState.value = HomeUiState.Loading
                delay(1000)
                val carList =
                    if (query.isBlank()) carRepository.getAllCars()
                    else carRepository.getCarsByQuery(query)
                if (carList.isEmpty() && query != "") {
                    _homeUiState.value = HomeUiState.NotFound(query = query)
                } else {
                    _homeUiState.value = HomeUiState.Success(listOfCars = carList)
                }
            } catch (e: Exception) {
                println(e)
                _homeUiState.value = HomeUiState.Error
            }
        }
    }

    fun repeat() {
        getCarList(userInput)
    }

}