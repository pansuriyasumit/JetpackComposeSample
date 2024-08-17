package com.fifteen11.jetpacksamplelibrary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fifteen11.jetpacksamplelibrary.data.local.UserLoginHistoryEntity
import com.fifteen11.jetpacksamplelibrary.data.local.repository.MedicineLocalRepository
import com.fifteen11.jetpacksamplelibrary.data.local.repository.MedicineLocalRepositoryImpl
import com.fifteen11.jetpacksamplelibrary.utils.SharedPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPrefs: SharedPreferenceManager,
    private val userRepository: MedicineLocalRepositoryImpl
) : ViewModel() {

    companion object {
        const val DEFAULT_USERNAME = "Admin User"
    }

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // Events
    private val _loginEvent = MutableSharedFlow<Unit>()
    val loginEvent: SharedFlow<Unit> = _loginEvent.asSharedFlow()

    fun onUsernameChanged(newUsername: String) {
        _uiState.value = _uiState.value.copy(username = newUsername)
    }

    fun onPasswordChanged(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun onLoginClick() {
        viewModelScope.launch {
            // Perform validation if needed
            insertLoginHistory()
            saveUserLogin()
            _loginEvent.emit(Unit) // Emit login event
        }
    }

    fun insertLoginHistory() = viewModelScope.launch(IO) {
        try {
            val username = _uiState.value.username.ifEmpty { DEFAULT_USERNAME }
            userRepository.insertUserLoginHistory(UserLoginHistoryEntity(userName = username))
        } catch (e: Exception) {
            // Handle error (e.g., log, show error message)
        }
    }

    fun saveUserLogin() {
        val username = _uiState.value.username.ifEmpty { DEFAULT_USERNAME }
        sharedPrefs.setIsUserLogin(true)
        sharedPrefs.setUserName(username)
    }

    data class LoginUiState(
        val username: String = "",
        val password: String = "",
        // Add other UI state properties as needed (e.g., isLoading, error)
    )
}