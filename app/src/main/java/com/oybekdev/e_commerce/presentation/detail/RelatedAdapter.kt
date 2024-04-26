package com.oybekdev.e_commerce.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.databinding.ItemProductRelatedBinding

class RelatedAdapter(
    private val products: List<Product>,
    private val onClick: (product: Product) -> Unit,
) : RecyclerView.Adapter<RelatedAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemProductRelatedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {
            Glide.with(root).load(product.image).into(image)

            name.text = product.title

            val current = product.price - (product.discount ?: 0.0)
            price.text = root.context.getString(R.string.price, current)
            old.text = root.context.getString(R.string.price_striked, product.price)
            old.isVisible = product.discount != null

            root.setOnClickListener {
                onClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  ViewHolder (
        ItemProductRelatedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }
}