package com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductResponseItem(

    @Json(name = "image") val image: String? = null,
    @Json(name = "price") val price: Double? = null,
    @Json(name = "rating") val rating: Rating? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "id") val id: Int? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "category") val category: String? = null
) : Parcelable

@Parcelize
data class Rating(

    @Json(name = "rate") val rate: Double? = null,
    @Json(name = "count") val count: Int? = null
) : Parcelable
