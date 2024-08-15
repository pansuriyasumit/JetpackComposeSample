package com.fifteen11.jetpacksamplelibrary.quotestesting

class ValidatePassword {

    fun isValidPassword(password: String): String {
        return if (password.isEmpty() || password.isBlank()) {
            "Password is empty"
        } else if (password.length < 6 || password.length > 15) {
            "Password length should be between 6 and 15"
        } else {
            "Valid Password"
        }
    }

    fun stringReverse(input: String?): String {
        if (input.isNullOrEmpty() || input.isBlank()) {
            throw IllegalArgumentException("Input string is required")
        } else {
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
}