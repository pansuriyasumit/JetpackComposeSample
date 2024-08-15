package com.fifteen11.jetpacksamplelibrary.quotestesting

import org.junit.Test

class ValidatePasswordTest {

    @Test
    fun isValidPassword_EmptyString_ReturnsPasswordIsEmpty() {
        val validatePassword = ValidatePassword()
        val result = validatePassword.isValidPassword("")
        assert(result == "Password is empty")
    }

    @Test
    fun isValidPassword_BlankString_ReturnsPasswordIsEmpty() {
        val validatePassword = ValidatePassword()
        val result = validatePassword.isValidPassword("   ")
        assert(result == "Password is empty")
    }

    @Test
    fun isValidPassword_PasswordLengthLessThan6_ReturnsPasswordLengthShouldBeBetween6And15() {
        val validatePassword = ValidatePassword()
        val result = validatePassword.isValidPassword("pass")
        assert(result == "Password length should be between 6 and 15")
    }

    @Test
    fun isValidPassword_PasswordLengthGreaterThan15_ReturnsPasswordLengthShouldBeBetween6And15() {
        val validatePassword = ValidatePassword()
        val result = validatePassword.isValidPassword("passwordpasswordpassword")
        assert(result == "Password length should be between 6 and 15")
    }

    @Test
    fun isValidPassword_ValidPassword_ReturnsValidPassword() {
        val validatePassword = ValidatePassword()
        val result = validatePassword.isValidPassword("password123")
        assert(result == "Valid Password")
    }
}