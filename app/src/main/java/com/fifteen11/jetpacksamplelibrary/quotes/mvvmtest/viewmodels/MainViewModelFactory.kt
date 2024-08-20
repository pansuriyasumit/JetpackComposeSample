package com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.repository.ProductRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val productRepository: ProductRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(productRepository) as T
    }
}