package com.fifteen11.jetpacksamplelibrary.quotestesting

import android.content.Context
import com.google.gson.Gson
import java.util.Arrays

class QuotesManager {

    var quoteList = emptyArray<Quotes>()
    var currentQuotesIndex = 0

    fun populateQuotes(context: Context, fileName: String) {
        //It will read the json file from the assets folder
        val inputStream = context.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        //It will convert the json file into string
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        quoteList = gson.fromJson(json, Array<Quotes>::class.java)
    }

    fun populateQuotes(quotes: Array<Quotes>) {
        //It will populate the quotes from the json file
        quoteList = quotes
    }

    fun getCurrentQuote(): Quotes {
        //It will return the current quote from the list
        return quoteList[currentQuotesIndex]
    }

    fun getNextQuote(): Quotes {
        //If we have last quote then we will return last quote. It will prevent ArrayOutOFBound error
        if (currentQuotesIndex == quoteList.size - 1) return quoteList[currentQuotesIndex]

        //Otherwise it will return the next quote
        return quoteList[++currentQuotesIndex]
    }

    fun getPreviousQuote(): Quotes {
        //If we have first(0th index) quote then we will return first quote. It will prevent ArrayOutOFBound error
        if (currentQuotesIndex == 0) return quoteList[currentQuotesIndex]

        //Otherwise it will return the previous quote
        return quoteList[--currentQuotesIndex]
    }
}