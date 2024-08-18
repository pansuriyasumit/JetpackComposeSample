package com.fifteen11.jetpacksamplelibrary.quotes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String,
    val author: String
)