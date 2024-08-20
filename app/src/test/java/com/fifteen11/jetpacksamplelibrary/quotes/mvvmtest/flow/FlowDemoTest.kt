package com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.flow

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Test

class FlowDemoTest {

    /**
     * Two types are Flow
     * 1. Cold Flow
     * 2. Hot Flow
     *
     * Cold Flow: If we have at least one subscriber then only execution will run
     * Hot Flow: It will doesn't wait for subscriber, it will emitting the value.
     *
     * Also, Flow either finite or infinite type.
     * -> Means, in case of finite after certain interval of time, our data will continue produce.
     * It will be close if there is not data for some time.
     *
     * -> But in case of infinite, we get the data continuously.
     *
     * Different Type of methods
     * 1. toList() -> It will convert the flow into list
     * 2. toCollection() -> It will convert the flow into collection
     * 3. toSet() -> It will convert the flow into set
     * 4. toTypedArray() -> It will convert the flow into array
     * 5. reduce() -> It will reduce the flow into single value
     * 6. fold() -> It will fold the flow into single value
     * 7. count() -> It will count the flow into single value
     * 8. collect() -> It will collect the flow into single value
     * 9. collectLatest() -> It will collect the latest flow into single value
     * 10. collectIndexed() -> It will collect the indexed flow into single value
     * 11. collectIn() -> It will collect the in flow into single value
     * 12. collectLatestIn() -> It will collect the latest in flow into single value
     * 13. take() -> It will take the flow into single value
     * 14. first() -> It will first the flow into single value
     * 15. last() -> It will last the flow into single value
     * 16. single() -> It will single the flow into single value
     * 17. singleOrNull() -> It will singleOrNull the flow into single value
     */
    @Test
    fun getFlowTest() = runTest {
        val sut = FlowDemo()
        sut.getFlow().toList()
        assertEquals(listOf(1, 2, 3, 4), sut.getFlow().toList().size)
    }
}