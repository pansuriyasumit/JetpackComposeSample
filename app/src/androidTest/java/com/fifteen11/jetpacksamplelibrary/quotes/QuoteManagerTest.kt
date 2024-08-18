package com.fifteen11.jetpacksamplelibrary.quotes

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.JsonSyntaxException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

/**
 * Instrumentation Test
 */
class QuoteManagerTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test(expected = FileNotFoundException::class)
    fun populateQuotes() {
        //Arrange Part
        val quotesManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()

        //Act Part
        quotesManager.populateQuotesFromAssets(context, "")

        //Assert Part
        assertEquals(6, quotesManager.quoteList.size)
    }

    @Test(expected = JsonSyntaxException::class)
    fun testPopulateQuotesFromAssets_InvalidJSON_expected_Exception() {
        //Arrange Part
        val quotesManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()

        //Act Part
        quotesManager.populateQuotesFromAssets(context, "malformed.json")
    }

    @Test(expected = JsonSyntaxException::class)
    fun testPopulateQuotesFromAssets_IllegalStateException_expected_JsonSyntaxException() {
        //Arrange Part
        val quotesManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()

        //Act Part
        quotesManager.populateQuotesFromAssets(context, "invalid.json")

        //Assert Part
        assertEquals(6, quotesManager.quoteList.size)
    }

    @Test
    fun testPopulateQuotesFromAssets_ValidJSON_expected_Count() {
        //Arrange Part
        val quotesManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()

        //Act Part
        quotesManager.populateQuotesFromAssets(context, "quotes.json")

        //Assert Part
        assertEquals(7, quotesManager.quoteList.size)
    }

    @Test
    fun testPreviousQuote_expected_CorrectQuote() {
        //Arrange Part
        val quotesManager = QuoteManager()
        quotesManager.populateQuotes(
            arrayOf(
                Quote("This is first quote", "First"),
                Quote("This is second quote", "Second"),
                Quote("This is third quote", "Third")
            )
        )

        //Act Part
        quotesManager.currentQuotesIndex = 1
        val quote = quotesManager.previousQuote()

        //Assert Part
        assertEquals("This is first quote", quote.text)
    }

    @Test
    fun testNextQuote_expected_CorrectQuote() {
        //Arrange Part
        val quotesManager = QuoteManager()
        quotesManager.populateQuotes(
            arrayOf(
                Quote("This is first quote", "First"),
                Quote("This is second quote", "Second"),
                Quote("This is third quote", "Third")
            )
        )

        //Act Part
        quotesManager.currentQuotesIndex = 1
        val quote = quotesManager.nextQuote()

        //Assert Part
        assertEquals("This is third quote", quote.text)
    }

    @Test
    fun testPreviousQuote_expected_FirstQuote() {
        //Arrange Part
        val quotesManager = QuoteManager()
        quotesManager.populateQuotes(
            arrayOf(
                Quote("This is first quote", "First"),
                Quote("This is second quote", "Second"),
                Quote("This is third quote", "Third")
            )
        )

        //Act Part
        quotesManager.currentQuotesIndex = 0
        val quote = quotesManager.getCurrentQuote()

        //Assert Part
        assertEquals("This is first quote", quote.text)
    }

    @Test
    fun testNextQuote_expected_LastQuote() {
        //Arrange Part
        val quotesManager = QuoteManager()
        quotesManager.populateQuotes(
            arrayOf(
                Quote("This is first quote", "First"),
                Quote("This is second quote", "Second"),
                Quote("This is third quote", "Third")
            )
        )

        //Act Part
        quotesManager.currentQuotesIndex = 2
        val quote = quotesManager.getCurrentQuote()

        //Assert Part
        assertEquals("This is third quote", quote.text)

    }

    @Test
    fun testPreviousQuote_expected_CorrectQuote_By_Author() {
        //Arrange Part
        val quotesManager = QuoteManager()
        quotesManager.populateQuotes(
            arrayOf(
                Quote("This is first quote", "First"),
                Quote("This is second quote", "Second"),
                Quote("This is third quote", "Third")
            )
        )

        //Act Part
        quotesManager.currentQuotesIndex = 1
        val quote = quotesManager.previousQuote()

        //Assert Part
        assertEquals("First", quote.author)
    }
}