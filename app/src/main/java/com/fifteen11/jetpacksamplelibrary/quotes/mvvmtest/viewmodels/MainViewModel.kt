package com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.models.ProductResponseItem
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.repository.ProductRepository
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.utils.NetworkResult
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _products = MutableLiveData<NetworkResult<List<ProductResponseItem>>>()
    val products: MutableLiveData<NetworkResult<List<ProductResponseItem>>>
        get() = _products

    fun getProductList() {
        viewModelScope.launch {
            val result = repository.getProducts()
            _products.postValue(result)
        }
    }
}