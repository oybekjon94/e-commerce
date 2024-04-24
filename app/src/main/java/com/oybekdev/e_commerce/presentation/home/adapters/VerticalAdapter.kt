package com.oybekdev.e_commerce.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.databinding.ItemProductBinding
import com.oybekdev.e_commerce.databinding.ItemProductHorizontalSectionBinding
import com.oybekdev.e_commerce.databinding.ItemProductVerticalSectionBinding
import com.oybekdev.e_commerce.databinding.ItemSectionVerticalBinding
import com.oybekdev.e_commerce.presentation.products.ProductViewHolder
import kotlin.math.roundToInt

class VerticalAdapter(
    private val products: List<Product>,
    private val onClick: (product: Product) -> Unit,
    private val like: (product: Product) -> Unit,
) : RecyclerView.Adapter<ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position],onClick,like)
    }
}