package com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.repository

import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.api.ProductAPI
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.models.ProductResponseItem
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ProductRepositoryTest {

    @Mock
    lateinit var productAPI: ProductAPI

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetProducts_EmptyList() = runTest {
        Mockito.`when`(productAPI.getProducts()).thenReturn(Response.success(emptyList()))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        assertEquals(true, result is NetworkResult.Success)
        assertEquals(0, result.data?.size)
    }

    @Test
    fun testGetProducts_expectedProductList() = runTest {
        val productList = listOf(
            ProductResponseItem("", 40.0, null, "", 1, "Prod 1", ""),
            ProductResponseItem("", 50.0, null, "", 2, "Prod 2", "")
        )

        Mockito.`when`(productAPI.getProducts()).thenReturn(Response.success(productList))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        assertEquals(true, result is NetworkResult.Success)
        assertEquals(2, result.data?.size)
        assertEquals("Prod 1", result.data?.get(0)?.title)
    }

    @Test
    fun testGetProducts_expectedError() = runTest {
        Mockito.`when`(productAPI.getProducts())
            .thenReturn(Response.error(401, "Unauthorized".toResponseBody()))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        assertEquals(true, result is NetworkResult.Error)
        assertEquals("Request failed with code 401", result.message)
    }

    @After
    fun tearDown() {

    }
}