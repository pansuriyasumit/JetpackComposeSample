package com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fifteen11.jetpacksamplelibrary.JetpackLibApp
import com.fifteen11.jetpacksamplelibrary.R
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.adapter.ProductListAdapter
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.utils.NetworkResult
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.viewmodels.MainViewModel
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.viewmodels.MainViewModelFactory

class ProductListActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        init()
        setData()
    }

    private fun init() {
        recyclerView = findViewById(R.id.rvProductList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun setData() {
        val repository = (application as JetpackLibApp).productRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getProductList()

        mainViewModel.products.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    adapter = ProductListAdapter(it.data!!)
                    recyclerView.adapter = adapter
                }

                is NetworkResult.Error -> {

                }

                is NetworkResult.Loading -> {

                }
            }
        }
    }
}