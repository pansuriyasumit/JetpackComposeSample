package com.fifteen11.jetpacksamplelibrary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fifteen11.jetpacksamplelibrary.data.local.UserLoginHistoryEntity
import com.fifteen11.jetpacksamplelibrary.data.local.repository.MedicineLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class UserHistoryViewModel @Inject constructor(
    private val localRepository: MedicineLocalRepository
) : ViewModel() {

    /*private val _loginHistory = MutableStateFlow(emptyList<UserLoginHistoryEntity>())
    val loginHistory = _loginHistory.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()*/

    private val _uiState = MutableStateFlow<UserHistoryUiState>(UserHistoryUiState.Loading)
    val uiState: StateFlow<UserHistoryUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(IO) {
            fetchLoginHistory()
        }
    }


    /*fun getUserLoginHistory() {
        viewModelScope.launch(IO) {
            try {
                localRepository.getUserLoginHistory().collect {
                    _loginHistory.value = it
                }
            } catch (ex: IOException) {
                _errorMessage.value = "Network error occurred"
            } catch (ex: Exception) {
                _errorMessage.value = "An unexpected error occurred"
            }
        }
    }*/

    private suspend fun fetchLoginHistory() {
        try {
            localRepository.getUserLoginHistory().collect { history ->
                _uiState.value = UserHistoryUiState.Success(history)
            }
        } catch (ex: IOException) {
            _uiState.value = UserHistoryUiState.Error("Network error occurred")
        } catch (ex: Exception) {
            _uiState.value = UserHistoryUiState.Error("An unexpected error occurred")
        }
    }

    sealed class UserHistoryUiState {
        object Loading : UserHistoryUiState()
        data class Success(val history: List<UserLoginHistoryEntity>) : UserHistoryUiState()
        data class Error(val message: String) : UserHistoryUiState()
    }
}
