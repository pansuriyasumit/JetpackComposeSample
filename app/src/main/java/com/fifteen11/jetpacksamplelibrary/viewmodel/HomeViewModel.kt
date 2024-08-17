package com.fifteen11.jetpacksamplelibrary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fifteen11.jetpacksamplelibrary.data.GreetingEnums
import com.fifteen11.jetpacksamplelibrary.data.model.MedicineResponse
import com.fifteen11.jetpacksamplelibrary.data.remote.repository.MedicineDataRepositoryImpl
import com.fifteen11.jetpacksamplelibrary.utils.SharedPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.time.Clock
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val medicineDataRepository: MedicineDataRepositoryImpl,
    private val sharedPreferenceManager: SharedPreferenceManager
) : ViewModel() {

    data class MedicineUiState(
        var medicines: MedicineResponse? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )

    private val _medicineUiState = MutableStateFlow(MedicineUiState())
    val medicineUiState: StateFlow<MedicineUiState>
        get() = _medicineUiState.asStateFlow()

    private val _userName = MutableStateFlow(getUserPreferenceData() ?: "")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _greetingMessage = MutableStateFlow(getGreetingMessage())
    val greetingMessage: StateFlow<String> = _greetingMessage.asStateFlow()

    var isMedicinesLoaded = false

    private fun getGreetingMessage(clock: Clock = Clock.systemDefaultZone()): String {
        val currentHour = LocalTime.now(clock).hour

        val greeting = when (currentHour) {
            in 0..11 -> GreetingEnums.GOOD_MORNING.greeting
            in 12..17 -> GreetingEnums.GOOD_AFTERNOON.greeting
            in 18..23 -> GreetingEnums.GOOD_EVENING.greeting
            else -> GreetingEnums.GOOD_NIGHT.greeting
        }

        return greeting
    }

    private fun getUserPreferenceData(): String? {
        return sharedPreferenceManager.getUserName()
    }

    fun fetchMedicines() {
        viewModelScope.launch {
            _medicineUiState.value = _medicineUiState.value.copy(isLoading = true)
            try {
                val response = medicineDataRepository.getMedicinesFormAPI()
                _medicineUiState.value = _medicineUiState.value.copy(medicines = response, isLoading = false)
            } catch (e: IOException) {
                _medicineUiState.value.medicines = null
                _medicineUiState.value = _medicineUiState.value.copy(error = "Network error", isLoading = false)
            } catch (e: HttpException) {
                _medicineUiState.value = _medicineUiState.value.copy(error = "API error", isLoading = false)
            } catch (e: Exception) {
                _medicineUiState.value =
                    _medicineUiState.value.copy(error = "An unexpected error occurred", isLoading = false)
            }
        }
    }

    fun clearUserData() {
        sharedPreferenceManager.clearPreferenceData()
        _userName.value = ""
    }
}