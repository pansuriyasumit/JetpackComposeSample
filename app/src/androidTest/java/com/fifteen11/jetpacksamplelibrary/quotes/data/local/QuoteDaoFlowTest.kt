package com.fifteen11.jetpacksamplelibrary.quotes.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.fifteen11.jetpacksamplelibrary.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * Our Test cases will be tun in isolation. If we are running one test case, it will not affected to other test cases.
 * We need to run independently.
 *
 * But here we are testing database and for that whenever test case is running, create a new instance of the database.
 * And perform some query on that database and write a test case based on that.
 *
 * For that, room database has 1 provision where we can create a in-memory database.
 */
@HiltAndroidTest
class QuoteDaoFlowTest {

    /**
     * InstantTaskExecutorRule is a JUnit rule that configures LiveData to execute each task synchronously on the calling thread.
     * It will help us to write test cases in a synchronous way.
     * It will execute one after another.
     * It will not block the main thread.
     * It will execute our architecture component code in a synchronous way.
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var quoteDatabase: QuoteDatabase
    lateinit var quoteDao: QuoteDao

    @Before
    fun setup() {
        hiltAndroidRule.inject() //It will inject all the injected class
        quoteDao = quoteDatabase.quoteDao()
    }

    /**
     * insertQuote function is our suspend function. So we need to use runBlocking.
     * It will block the current thread until the coroutine is completed.
     */
    @Test
    fun insertQuote_expectedSingleQuote() = runBlocking {
        val quote = QuoteEntity(0, "This is a test quote", "Test")
        quoteDao.insertQuote(quote)

        val result = quoteDao.getQuotesUsingFlow().first()

        assertEquals(1, result.size)
        assertEquals("This is a test quote", result[0].text)
    }

    @Test
    fun insertQuote_expectedSingleQuote_with_Turbine() = runBlocking {
        val quote = QuoteEntity(0, "This is a test quote", "Test")
        val quote2 = QuoteEntity(0, "This is a test quote Two", "Test Fifteen11")

        quoteDao.insertQuote(quote)

        //Here, first quote one is insert and after 500 millisecond, second quote is insert.
        launch {
            delay(500L)
            quoteDao.insertQuote(quote2)
        }

        //Turbine is for testing Flow when we use Coroutines and flow is continuously emitting data
        val result = quoteDao.getQuotesUsingFlow().test {

            val quoteList = awaitItem() // This function will wait for the first emission of the flow and store it in quoteList
            assertEquals(1, quoteList.size)
            assertEquals("This is a test quote", quoteList[0].text)

            val quoteList2 = awaitItem() // This function will wait for the second emission of the flow and store it in quoteList2
            assertEquals(2, quoteList2.size)
            assertEquals("This is a test quote Two", quoteList2[1].text)

            cancel() // This function will cancel the flow and consume all remaining events
        }
    }

    @After
    fun tearDown() {
        quoteDatabase.close()
    }



}