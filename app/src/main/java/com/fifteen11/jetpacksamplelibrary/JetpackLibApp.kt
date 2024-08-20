package com.fifteen11.jetpacksamplelibrary

import android.app.Application
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.api.ProductAPI
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.repository.ProductRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@HiltAndroidApp
class JetpackLibApp : Application() {

    lateinit var productAPI: ProductAPI
    lateinit var productRepository: ProductRepository
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://fakestoreapi.com/")
            .build()

        productAPI = retrofit.create(ProductAPI::class.java)
        productRepository = ProductRepository(productAPI)
    }
}