package com.fifteen11.jetpacksamplelibrary.quotes.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Constructor Injection
 */
class Utils(val dispatcher: CoroutineDispatcher) {

    suspend fun getUserName(): String {
        delay(10000L)
        return "fifteen11"
    }

    suspend fun getUser(): String {
        CoroutineScope(dispatcher).launch {
            delay(5000L)
        }
        return "User - fifteen11"
    }

    suspend fun getAddress(): String {
        CoroutineScope(dispatcher).launch {
            delay(5000L)
        }
        return "fifteen11, India"
    }

    var globalArgs = false
    fun getAddressDetails() {
        CoroutineScope(dispatcher).launch {
            globalArgs = true
        }
    }
}