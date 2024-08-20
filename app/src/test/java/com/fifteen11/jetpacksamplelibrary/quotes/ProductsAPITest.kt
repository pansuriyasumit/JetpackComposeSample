package com.fifteen11.jetpacksamplelibrary.quotes

import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.api.ProductAPI
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

class ProductsAPITest {

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
    fun testGetAllProducts() = runTest {
        val mockResponse = MockResponse() // Create a mock response
        mockResponse.setBody("[]") // Set the response body to an empty JSON array
        mockWebServer.enqueue(mockResponse) // Enqueue the mock response

        val response = productAPI.getProducts()
        mockWebServer.takeRequest() // Take the request made by the API

        assertEquals(true, response.body()?.isEmpty())
    }

    @Test
    fun testGetAllProducts_return_products() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")

        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = productAPI.getProducts()
        mockWebServer.takeRequest()

        assertEquals(false, response.body()?.isEmpty())
        assertEquals(2, response.body()?.size)
    }

    @Test
    fun testGetAllProducts_return_error() = runTest{
        val mockResponse = MockResponse()

        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockWebServer.enqueue(mockResponse)

        val response = productAPI.getProducts()
        mockWebServer.takeRequest()

        assertEquals(false, response.isSuccessful)

        assertEquals(404, response.code())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}