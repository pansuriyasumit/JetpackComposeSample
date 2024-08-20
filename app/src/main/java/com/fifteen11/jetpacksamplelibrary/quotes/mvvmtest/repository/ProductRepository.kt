package com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.repository

import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.api.ProductAPI
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.models.ProductResponseItem
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.utils.NetworkResult

class ProductRepository(private val productAPI: ProductAPI) {

    suspend fun getProducts(): NetworkResult<List<ProductResponseItem>> {

        val response = productAPI.getProducts()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkResult.Success(responseBody)
            } else {
                NetworkResult.Error("Something went wrong")
            }
        } else {
            NetworkResult.Error("Request failed with code ${response.code()}")
        }
    }
}