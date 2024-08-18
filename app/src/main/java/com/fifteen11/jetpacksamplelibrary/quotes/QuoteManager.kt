package com.fifteen11.jetpacksamplelibrary.quotes

import android.content.Context
import com.google.gson.Gson

class QuoteManager {

    var quoteList = emptyArray<Quote>()
    var index = 0
    var currentQuotesIndex = 0

    fun populateQuotesFromAssets(context: Context, fileName: String) {
        val inputStream = context.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        quoteList = gson.fromJson(json, Array<Quote>::class.java)
    }

    fun populateQuotes(quotes: Array<Quote>) {
        quoteList = quotes
    }

    fun getCurrentQuote(): Quote {
        return quoteList[currentQuotesIndex]
    }

    fun nextQuote(): Quote {
        if (currentQuotesIndex == quoteList.size - 1) return quoteList[currentQuotesIndex]
        return quoteList[++currentQuotesIndex]
    }

    fun previousQuote(): Quote {
        if (currentQuotesIndex == 0) return quoteList[currentQuotesIndex]
        return quoteList[--currentQuotesIndex]
    }
}