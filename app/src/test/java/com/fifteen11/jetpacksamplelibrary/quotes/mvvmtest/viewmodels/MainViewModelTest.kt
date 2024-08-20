package com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fifteen11.jetpacksamplelibrary.quotes.MainCoroutineRule
import com.fifteen11.jetpacksamplelibrary.quotes.getOrAwaitValue
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.repository.ProductRepository
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ProductRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetProducts_with_EmptyList() = runTest {
        Mockito.`when`(repository.getProducts()).thenReturn(NetworkResult.Success(emptyList()))
        val sut = MainViewModel(repository)
        sut.getProductList()

        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()

        val result = sut.products.getOrAwaitValue()
        assertEquals(0, result.data?.size)
    }

    @Test
    fun testGetProducts_with_ExpectedError() = runTest {

        Mockito.`when`(repository.getProducts()).thenReturn(NetworkResult.Error("Something went wrong"))
        val sut = MainViewModel(repository)
        sut.getProductList()

        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()

        val result = sut.products.getOrAwaitValue()
        assertEquals(true, result is NetworkResult.Error)
        assertEquals("Something went wrong", result.message)
    }

    @After
    fun tearDown() {

    }
}