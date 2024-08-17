package com.fifteen11.jetpacksamplelibrary

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * `TestScope`: This is the new recommended way to manage coroutines in tests.
 * It allows you to control the execution of coroutines and provides a more consistent API for testing.
 *
 * `runTest`: This function automatically manages the TestCoroutineScheduler and provides
 * a structured way to write coroutine-based tests.
 */

@ExperimentalCoroutinesApi
class TestCoroutineRule(
    private val testDispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() {

    val testScope = TestScope(testDispatcher)

    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }

    fun runTest(block: suspend TestScope.() -> Unit) = testScope.runTest { block() }
}