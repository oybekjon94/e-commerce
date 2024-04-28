package com.oybekdev.e_commerce.presentation.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.databinding.ItemProductSearchBinding

class SearchProductsAdapter(
    private val onClick:(product:Product) -> Unit,
    private val like:(product:Product) -> Unit
) :PagingDataAdapter<Product, SearchProductViewHolder>(DIFF_UTIL){

    //to efficiently handle item changes and animations when the underlying dataset changes
    companion object{
        private val DIFF_UTIL = object :DiffUtil.ItemCallback<Product>(){
            override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: SearchProductViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return ,onClick,like)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchProductViewHolder(
        ItemProductSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )
}