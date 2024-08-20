package com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.api

import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.models.ProductResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {

    @GET("/products")
    suspend fun getProducts(): Response<List<ProductResponseItem>>
}