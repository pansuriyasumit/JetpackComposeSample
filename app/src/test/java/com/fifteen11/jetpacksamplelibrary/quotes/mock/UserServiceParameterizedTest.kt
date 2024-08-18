package com.fifteen11.jetpacksamplelibrary.quotes.mock

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


/**
 * Parameterized tests run the testUserService method multiple times with different combinations of
 * loginStatus and expectedStatus from the data() method.
 *
 * While [sut] is a common abbreviation for "system under test", consider using a more descriptive name
 * like userService for better readability, especially for those unfamiliar with the abbreviation.
 *
 * If there's logic in UserService that depends on the specific email or password,
 * use actual values instead of anyString() in your Mockito.when statements to make your tests more precise.
 */

@RunWith(Parameterized::class)
class UserServiceParameterizedTest(private val loginStatus: LOGIN_STATUS, private val expectedStatus: String) {

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testUserService() {
        Mockito.`when`(userRepository.loginUser(anyString(), anyString())).thenReturn(loginStatus)
        val sut = UserService(userRepository)
        val status = sut.loginUSer("abc@gmail.com", "12345678900")
        assertEquals(expectedStatus, status)
    }

    companion object {

        @JvmStatic
        @Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(LOGIN_STATUS.INVALID_PASSWORD, "Password is invalid"),
                arrayOf(LOGIN_STATUS.INVALID_USER, "User does not exist"),
                arrayOf(LOGIN_STATUS.UNKNOWN_ERROR, "Unknown error occurred"),
                arrayOf(LOGIN_STATUS.SUCCESS, "Logged in successfully")
            )
        }
    }
}