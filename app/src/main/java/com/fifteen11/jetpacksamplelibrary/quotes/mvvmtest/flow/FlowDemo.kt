package com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowDemo {

    fun getFlow() = flow<Int> {
        emit(1)
        delay(2000)
        emit(2)
        delay(2000)
        emit(3)
        delay(2000)
        emit(4)
    }
}