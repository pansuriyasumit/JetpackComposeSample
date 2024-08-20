package com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fifteen11.jetpacksamplelibrary.R
import com.fifteen11.jetpacksamplelibrary.quotes.mvvmtest.models.ProductResponseItem

class ProductListAdapter(private val productList: List<ProductResponseItem>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductListAdapter.ViewHolder, position: Int) {
        val product = productList[position]
        holder.productTitle.text = product.title
        Glide.with(holder.productImage.context).load(product.image).into(holder.productImage)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage = itemView.findViewById<AppCompatImageView>(R.id.productImage)
        val productTitle = itemView.findViewById<AppCompatTextView>(R.id.productTitle)
    }

}