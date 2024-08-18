package com.fifteen11.jetpacksamplelibrary.quotes.mock

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserServiceTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    /**
     * We Need to Test [UserService]. But [UserService] dependency is on the [UserRepository]
     * In [UserRepository] we have 1 function loginUSer() and it will return the status.
     *
     * In [UserService] class, based on status we need to get the messages. which will use to display on UI screen.
     * So for that we create a mock object of [UserRepository]
     *
     * Alternative way is [UserServiceParameterizedTest]
     */
    @Test
    fun testUserService() {
        Mockito.`when`(userRepository.loginUser(anyString(), anyString())).thenReturn(LOGIN_STATUS.INVALID_PASSWORD)
        val sut = UserService(userRepository)
        val status = sut.loginUSer("abc@gmail.com", "12345678900")
        assertEquals("Password is invalid", status)
    }

    @Test
    fun testUserService_invalid_user() {
        Mockito.`when`(userRepository.loginUser(anyString(), anyString())).thenReturn(LOGIN_STATUS.INVALID_USER)
        val sut = UserService(userRepository)
        val status = sut.loginUSer("abc@gmail.com", "12345678900")
        assertEquals("User does not exist", status)
    }

    @Test
    fun testUserService_unknown_error() {
        Mockito.`when`(userRepository.loginUser(anyString(), anyString())).thenReturn(LOGIN_STATUS.UNKNOWN_ERROR)
        val sut = UserService(userRepository)
        val status = sut.loginUSer("abc@gmail.com", "12345678900")
        assertEquals("Unknown error occurred", status)
    }

    @Test
    fun testUserService_success() {
        Mockito.`when`(userRepository.loginUser(anyString(), anyString())).thenReturn(LOGIN_STATUS.SUCCESS)
        val sut = UserService(userRepository)
        val status = sut.loginUSer("abc@gmail.com", "12345678900")
        assertEquals("Logged in successfully", status)
    }
}