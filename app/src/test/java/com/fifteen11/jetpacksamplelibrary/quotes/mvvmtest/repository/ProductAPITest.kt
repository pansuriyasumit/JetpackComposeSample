package com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.repository

import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.api.ProductAPI
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.utils.NetworkResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ProductAPITest {

    lateinit var mockWebServer: MockWebServer
    lateinit var productAPI: ProductAPI

    @Before
    fun setup() {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        mockWebServer = MockWebServer()
        productAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ProductAPI::class.java)
    }

    @Test
    fun testGetProducts_EmptyList() = runTest {
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setResponseCode(200).setBody("[]"))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        val request = mockWebServer.takeRequest()


        assertEquals(true, result is NetworkResult.Success)
        assertEquals(0, result.data?.size)
    }

    @Test
    fun testGetProducts_return_Error() = runTest {
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setResponseCode(400))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        val request = mockWebServer.takeRequest()

        assertEquals(true, result is NetworkResult.Error)
        assertEquals(null, result.data?.size)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}