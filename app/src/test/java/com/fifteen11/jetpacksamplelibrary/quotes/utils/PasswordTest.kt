package com.fifteen11.jetpacksamplelibrary.quotes.utils

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PasswordTest {

    private lateinit var helper: Helper

    @Before
    fun setUp() {
        helper = Helper()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun validatePassword_blankInput_expectedRequiredField() {
        val result = helper.validatePassword("     ")
        assertEquals("Password is empty", result)
    }

    @Test
    fun validatePassword_2CharInput_expectedValidationError() {
        val result = helper.validatePassword("ab")
        assertEquals("Password length should be greater than 6", result)
    }

    @Test
    fun validatePassword_CorrectInput_expectedSuccess() {
        val result = helper.validatePassword("abcdef")
        assertEquals("Password is valid", result)
    }

    @Test
    fun validatePassword_16CharInput_expectedValidationError() {
        val result = helper.validatePassword("abcdefghijklnmop")
        assertEquals("Password length should be less than 15", result)
    }

    @Test
    fun validatePassword_emptyInput_expectedRequiredField() {
        val result = helper.validatePassword("")
        assertEquals("Password is empty", result)
    }

    @Test
    fun validatePassword_nullInput_expectedRequiredField() {
        val result = helper.validatePassword(null)
        assertEquals("Password is empty", result)
    }

    @Test
    fun validatePassword_correctInput_expectedSuccess() {
        val result = helper.validatePassword("abcdef")
        assertEquals("Password is valid", result)
    }
}