package com.fifteen11.jetpacksamplelibrary.quotes.utils

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HelperTest {

    private lateinit var helper: Helper

    @Before
    fun setUp() {
        helper = Helper()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun isPalindromeTest() {
        //Arrange

        //Act
        val result = helper.isPalindrome("hello")

        //Assert
        assertEquals(false, result)
    }

    @Test
    fun isPalindromeTest_inputString_level_expected_true() {
        //Arrange

        //Act
        val result = helper.isPalindrome("MADAM")

        //Assert
        assertEquals(true, result)
    }

    @Test
    fun isPalindromeTest_inputString_empty_expected_true() {
        //Arrange
        val helper = Helper()

        //Act
        val result = helper.isPalindrome("")

        //Assert
        assertEquals(true, result)
    }

    @Test
    fun isPalindromeTest_inputString_singleCharacter_expected_true() {
        //Arrange
        val helper = Helper()

        //Act
        val result = helper.isPalindrome("a")

        //Assert
        assertEquals(true, result)
    }



}