package com.fifteen11.jetpacksamplelibrary.viewmodel

import com.fifteen11.jetpacksamplelibrary.TestCoroutineRule
import com.fifteen11.jetpacksamplelibrary.data.local.UserLoginHistoryEntity
import com.fifteen11.jetpacksamplelibrary.data.local.repository.MedicineLocalRepository
import com.fifteen11.jetpacksamplelibrary.data.local.repository.MedicineLocalRepositoryImpl
import com.fifteen11.jetpacksamplelibrary.utils.SharedPreferenceManager
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.Date

/**
 * 1. Local Unit Test: This test runs on the JVM without the need for an Android device or emulator.
 *
 * 2. Mocking: We use Mockito to create mock objects for [SharedPreferenceManager] and [MedicineLocalRepository],
 * isolating the [LoginViewModel] for testing.
 *
 * 3. Coroutines Testing: The [runTest] function from the [kotlinx-coroutines-test]
 * library is used to test coroutine behavior.
 *
 * 4. Assertions: We use JUnit's [assertEquals], [assertNotNull], and Mockito's [verify] to validate
 * the expected behavior of the ViewModel.
 */

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPrefs: SharedPreferenceManager // Mocked
    private lateinit var userRepository : MedicineLocalRepositoryImpl // Mocked

    @Before
    fun setup() {
        //Initialize mock objects
        sharedPrefs = mock(SharedPreferenceManager::class.java)
        userRepository  = mock(MedicineLocalRepositoryImpl::class.java)

        //Inject mocks into the ViewModel
        loginViewModel = LoginViewModel(sharedPrefs, userRepository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `onUsernameChanged updates username in uiState`() {
        val newUsername = "testuser"
        loginViewModel.onUsernameChanged(newUsername)

        assertEquals(newUsername, loginViewModel.uiState.value.username)
    }

    @Test
    fun `onPasswordChanged updates password in uiState`() {
        val newPassword = "testpassword"
        loginViewModel.onPasswordChanged(newPassword)

        assertEquals(newPassword, loginViewModel.uiState.value.password)
    }

}