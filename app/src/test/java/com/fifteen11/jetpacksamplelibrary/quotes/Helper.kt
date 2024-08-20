package com.fifteen11.jetpacksamplelibrary.quotes

import java.io.InputStreamReader

object Helper {

    fun readFileResource(fileName: String): String {
        val inputStream = Helper::class.java.getResourceAsStream(fileName)
        val stringBuilder = StringBuilder()
        val reader = InputStreamReader(inputStream, Charsets.UTF_8)
        reader.readLines().forEach {
            stringBuilder.append(it)
        }
        return stringBuilder.toString()
    }
}