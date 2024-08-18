package com.fifteen11.jetpacksamplelibrary.quotes.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.fifteen11.jetpacksamplelibrary.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Our Test cases will be tun in isolation. If we are running one test case, it will not affected to other test cases.
 * We need to run independently.
 *
 * But here we are testing database and for that whenever test case is running, create a new instance of the database.
 * And perform some query on that database and write a test case based on that.
 *
 * For that, room database has 1 provision where we can create a in-memory database.
 */
class QuoteDaoTest {

    /**
     * InstantTaskExecutorRule is a JUnit rule that configures LiveData to execute each task synchronously on the calling thread.
     * It will help us to write test cases in a synchronous way.
     * It will execute one after another.
     * It will not block the main thread.
     * It will execute our architecture component code in a synchronous way.
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var quoteDatabase: QuoteDatabase
    lateinit var quoteDao: QuoteDao

    @Before
    fun setup() {
        quoteDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuoteDatabase::class.java
        ).allowMainThreadQueries().build()
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

        //Here, getQuotes() return the LiveData and block the current thread until the LiveData is updated.
        val result = quoteDao.getQuotes().getOrAwaitValue()

        assertEquals(1, result.size)
        assertEquals("This is a test quote", result[0].text)
    }

    @Test
    fun deleteQuote_expectedNoResults() = runBlocking {
        val quote = QuoteEntity(0, "This is a test quote", "Test")
        quoteDao.insertQuote(quote)
        quoteDao.deleteAllQuotes()

        val result = quoteDao.getQuotes().getOrAwaitValue()
        assertEquals(0, result.size)
    }

    @Test
    fun updateQuote_expected() = runBlocking {
        val quote = QuoteEntity(0, "This is a test quote", "Test")
        quoteDao.insertQuote(quote)

        quoteDao.updateQuote(QuoteEntity(1, "This is a test quote updated", "Test"))

        val result = quoteDao.getQuotes().getOrAwaitValue()
        assertEquals(1, result.size)
        assertEquals("This is a test quote updated", result[0].text)
    }

    @Test
    fun testQuoteById_expected() = runBlocking {
        val quote = QuoteEntity(0, "This is a test quote", "Test")
        quoteDao.insertQuote(quote)

        val result = quoteDao.getQuoteById(1)
        assertEquals("This is a test quote", result.text)
    }

    @After
    fun tearDown() {
        quoteDatabase.close()
    }



}