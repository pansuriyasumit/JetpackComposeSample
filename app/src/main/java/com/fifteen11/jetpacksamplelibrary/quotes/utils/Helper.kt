package com.fifteen11.jetpacksamplelibrary.quotes.utils

class Helper {

    fun isPalindrome(input: String): Boolean {

        var i = 0;
        var j = input.length - 1
        var result = true

        while (i < j) {
            if (input[i] != input[j]) {
                result = false
                break
            }
            i++
            j--
        }
        return result
    }

    fun validatePassword(password: String?): String {
        when {
            password == null -> {
                return "Password is null"
            }
            password.isEmpty() || password.isBlank() -> {
                return "Password is empty"
            }
            password.length < 6 -> {
                return "Password length should be greater than 6"
            }
            password.length > 15 -> {
                return "Password length should be less than 15"
            }
            else -> {
                return "Password is valid"
            }
        }
    }

    fun reverseString(input: String?): String {
        if (input.isNullOrEmpty() || input.isBlank()) {
            throw IllegalArgumentException("Input cannot be null")
        }

        val charArray = input.toCharArray()
        var i = 0
        var j = charArray.size - 1
        while (i < j) {
            val temp = charArray[i]
            charArray[i] = charArray[j]
            charArray[j] = temp
            i++
            j--
        }
        return charArray.joinToString("")
    }
}