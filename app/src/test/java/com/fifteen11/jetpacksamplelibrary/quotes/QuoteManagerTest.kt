package com.fifteen11.jetpacksamplelibrary.quotes

import android.content.Context
import android.content.res.AssetManager
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn

/**
 * This is Local Test or JVM Test
 */
class QuoteManagerTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var assetsManager: AssetManager

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testCurrentQuote_expected_CorrectQuote() {
        val testStream = QuoteManagerTest::class.java.getResourceAsStream("/quotes.json")
        // Using this [context] and [assetsManager] object will link.
        // When asset's context property is access that time return the [assetsManager] object.
        // [assetsManager] is one mock object.
        doReturn(assetsManager).`when`(context).assets

        //Define the behavior of the mock object
        Mockito.`when`(assetsManager.open(anyString())).thenReturn(testStream)

        val sut = QuoteManager()
        sut.populateQuotesFromAssets(context, "")

        val quote = sut.getCurrentQuote()
        assertEquals("Genius is one percent inspiration and ninety-nine percent perspiration.", quote.text)
    }

    @Test
    fun testPreviousQuote_expected_CorrectQuote() {
        val testStream = QuoteManagerTest::class.java.getResourceAsStream("/quotes.json")
        doReturn(assetsManager).`when`(context).assets
        Mockito.`when`(assetsManager.open(anyString())).thenReturn(testStream)

        val sut = QuoteManager()
        sut.currentQuotesIndex = 2
        sut.populateQuotesFromAssets(context, "")

        val quote = sut.previousQuote()
        assertEquals("You can observe a lot just by watching.", quote.text)
    }

    @Test
    fun testNextQuote_expected_CorrectQuote() {
        val testStream = QuoteManagerTest::class.java.getResourceAsStream("/quotes.json")
        doReturn(assetsManager).`when`(context).assets
        Mockito.`when`(assetsManager.open(anyString())).thenReturn(testStream)

        val sut = QuoteManager()
        sut.currentQuotesIndex = 1
        sut.populateQuotesFromAssets(context, "")

        val quote = sut.nextQuote()
        assertEquals("A house divided against itself cannot stand.", quote.text)
    }

    @After
    fun tearDown() {
        assetsManager.close()
    }
}