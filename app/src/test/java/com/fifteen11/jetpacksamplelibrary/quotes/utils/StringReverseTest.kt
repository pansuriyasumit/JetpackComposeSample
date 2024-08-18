package com.fifteen11.jetpacksamplelibrary.quotes.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class StringReverseTest {

    @Test
    fun reverseString() {
        val reverseString = Helper()
        val result = reverseString.reverseString("ABC")
        assertEquals("CBA", result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun reverseString_EmptyString_ReturnsEmptyString() {
        val reverseString = Helper()
        reverseString.reverseString("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun reverseString_BlankString_ReturnsEmptyString() {
        val reverseString = Helper()
        reverseString.reverseString("   ")
    }

    @Test
    fun reverseString_SingleCharacterString_ReturnsSameString() {
        val reverseString = Helper()
        val result = reverseString.reverseString("A")
        assertEquals("A", result)
    }

    @Test
    fun reverseString_MultipleCharacterString_ReturnsReversedString() {
        val reverseString = Helper()
        val result = reverseString.reverseString("ABCDEF")
        assertEquals("FEDCBA", result)
    }

    @Test
    fun reverseString_SpecialCharacters_ReturnsReversedString() {
        val reverseString = Helper()
        val result = reverseString.reverseString("!@#$%^&*()")
        assertEquals(")(*&^%$#@!", result)
    }

    @Test
    fun reverseString_Numbers_ReturnsReversedString() {
        val reverseString = Helper()
        val result = reverseString.reverseString("12345")
        assertEquals("54321", result)
    }

    @Test
    fun reverseString_MixedCase_ReturnsReversedString() {
        val reverseString = Helper()
        val result = reverseString.reverseString("aBcDeF")
        assertEquals("FeDcBa", result)
    }

    @Test
    fun reverseString_MixedCaseWithNumber_ReturnsReversedString() {
        val reverseString = Helper()
        val result = reverseString.reverseString("aB1c2D3")
        assertEquals("3D2c1Ba", result)
    }

    @Test
    fun reverseString_LongString_ReturnsReversedString() {
        val reverseString = Helper()
        val result = reverseString.reverseString("This is a long string")
        assertEquals("gnirts gnol a si sihT", result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun reverseString_NullString_ReturnsEmptyString() {
        val reverseString = Helper()
        reverseString.reverseString(null)
    }

    @Test(expected = IllegalArgumentException::class)
    fun reverseString_NullString_ThrowsIllegalArgumentException() {
        val reverseString = Helper()
        reverseString.reverseString(null)
    }
}