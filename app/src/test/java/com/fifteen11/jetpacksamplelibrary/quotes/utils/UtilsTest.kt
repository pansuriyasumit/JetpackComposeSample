package com.fifteen11.jetpacksamplelibrary.quotes.utils

import com.fifteen11.jetpacksamplelibrary.quotes.MainCoroutineRule
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

/**
 * This is JVM Test or Local Test
 *
 * Refer @see https://developer.android.com/kotlin/coroutines/test
 */

class UtilsTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    /**
     * [runBlocking]
     * runBlocking -- block the thread until the coroutine is completed.
     * runBlocking is not a good practice to use test the production code.
     * We can't avoid the delay so that is not a good practice.
     */
    @Test
    fun testGetUser() {
        /*val sut = Utils()
        runBlocking {
            sut.getUserName()
        }*/
    }

    /**
     * [runTest]
     * runTest --> It will launch new Coroutine which is designed for testing.
     * It will create a new TestScope(Coroutines) which is used for testing.
     *
     * It will prevent the delay so we can test the production code as much as faster.
     */
    @Test
    fun testGetUserUsingRunTest() {
        /*val sut = Utils()
        runTest {
            sut.getUserName()
        }*/
    }

    /**
     * It will give an Error -> Because we have no main dispatcher in JVM.
     * Because [getUser] function is suspend function and execute on Main Dispatcher.
     *
     * In testing we does not have any Main Dispatcher.
     *
     * For tests Dispatchers.setMain from kotlinx-coroutines-test module can be used
     */
    @Test(expected = IllegalStateException::class)
    fun testGetUser_using_CoroutineTest_with_Error() {
        /*val sut = Utils()
        runTest {
            sut.getUser()
        }*/
    }

    @Test
     fun testGetUser_using_CoroutineTest() {
        val sut = Utils(mainCoroutineRule.testDispatcher)
        runTest {
            sut.getUser()
        }
    }

    @Test
    fun testGetAddress_using_CoroutineTest() {
        val sut = Utils(mainCoroutineRule.testDispatcher)
        runTest {
            sut.getAddress()
        }
    }

    /**
     * @Test: This annotation indicates that the following function is a test function.
     *
     *
     * fun testGetAddressDetails_using_CoroutineTest() { ... }: This defines the test function itself.
     * The name suggests it's testing a function called getAddressDetails within a class likely named Utils.
     *
     *
     * val sut = Utils(mainCoroutineRule.testDispatcher): This line creates an instance of the Utils class.
     * The constructor takes a [CoroutineDispatcher] as an argument, and here it's being provided with [mainCoroutineRule.testDispatcher].
     * This likely means that [mainCoroutineRule] is a test rule (possibly a JUnit rule)
     * that provides a [TestDispatcher] for controlling coroutines during testing.
     *
     *
     * runTest { ... }: This function, provided by [kotlinx-coroutines-test], creates a coroutine scope specifically
     * designed for testing. Code within this block will run with a [TestDispatcher] and a virtual time source,
     * allowing you to control the execution of coroutines.
     *
     *
     * sut.getAddressDetails(): This line calls the function being tested.
     * It's assumed that [getAddressDetails] is a suspending function or launches coroutines internally.
     *
     *
     * mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle(): This is a key part of testing with coroutines.
     * It advances the virtual time of the [TestCoroutineScheduler] associated with the [TestDispatcher]
     * until all scheduled tasks are completed. This ensures that any coroutines launched by
     * [getAddressDetails] have a chance to finish before assertions are made.
     *
     *
     * Assert.assertEquals(true, sut.globalArgs): This is the assertion of the test.
     * It checks if the value of sut.globalArgs is true.
     * This suggests that getAddressDetails is expected to modify this property.
     *
     */
    @Test
    fun testGetAddressDetails_using_CoroutineTest() {
        val sut = Utils(mainCoroutineRule.testDispatcher)
        runTest {
            sut.getAddressDetails()
            mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
            Assert.assertEquals(true, sut.globalArgs)
        }
    }
}