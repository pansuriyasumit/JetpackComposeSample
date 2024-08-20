package com.fifteen11.jetpacksamplelibrary.quotes.di

import android.content.Context
import androidx.room.Room
import com.fifteen11.jetpacksamplelibrary.quotes.data.local.QuoteDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    fun provideDatabase(@ApplicationContext context: Context): QuoteDatabase {
        return Room.databaseBuilder(context, QuoteDatabase::class.java, "quotesDB").build()
    }
}