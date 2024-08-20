package com.fifteen11.jetpacksamplelibrary.di

import android.content.Context
import androidx.room.Room
import com.fifteen11.jetpacksamplelibrary.quotes.data.local.QuoteDatabase
import com.fifteen11.jetpacksamplelibrary.quotes.di.DatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * When our test case is running,
 *  - In our SingletonComponent we have DataBase module
 *  - It will replace by the TestDatabaseModule
 */
@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
@Module
class TestDatabaseModule {

    @Singleton
    @Provides
    fun provideTestDatabase(@ApplicationContext context: Context): QuoteDatabase {
        //TestDatabaseModule will replace the DatabaseModule
        return Room.inMemoryDatabaseBuilder(
            context,
            QuoteDatabase::class.java
        ).allowMainThreadQueries().build()
    }
}