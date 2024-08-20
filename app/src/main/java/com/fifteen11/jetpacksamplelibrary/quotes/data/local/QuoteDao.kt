package com.fifteen11.jetpacksamplelibrary.quotes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * For Room Database Testing we required Context, So it will be in Instrumentation Test, [androidTest] Directory.
 */
@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQuote(quote: QuoteEntity)

    @Update
    suspend fun updateQuote(quote: QuoteEntity)

    @Query("DELETE FROM quotes")
    suspend fun deleteAllQuotes()

    @Query("SELECT * FROM quotes")
    fun getQuotes(): LiveData<List<QuoteEntity>>

    @Query("SELECT * FROM quotes")
    fun getQuotesUsingFlow(): Flow<List<QuoteEntity>>


    @Query("SELECT * FROM quotes WHERE id = :quoteId")
    suspend fun getQuoteById(quoteId: Int): QuoteEntity
}