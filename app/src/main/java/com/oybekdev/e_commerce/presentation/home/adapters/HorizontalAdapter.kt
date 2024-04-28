package com.oybekdev.e_commerce.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.databinding.ItemProductHorizontalSectionBinding
import kotlin.math.roundToInt

class HorizontalAdapter(
    private val products: List<Product>,
    private val onClick: (product: Product) -> Unit,
    private val wishlist: (product: Product) -> Unit,
) : RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProductHorizontalSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {
            Glide.with(root).load(product.image).into(image)

            discount.isVisible = product.discount != null
            product.discount?.let {
                val percent = (product.discount / product.price * 100).roundToInt()
                binding.discount.text =
                    root.context.getString(R.string.fragment_item_product_discount, percent)
            }

            name.text = product.title
            rating.text = String.format("%.1f", product.rating)
            ratingCount.text =
                root.context.getString(R.string.item_product_ratings_count, product.ratingCount)

            val current = product.price - (product.discount ?: 0.0)
            price.text = root.context.getString(R.string.price, current)
            old.text = root.context.getString(R.string.price_striked, product.price)
            old.isVisible = product.discount != null

            root.setOnClickListener {
                onClick(product)
            }

            fun setLike() {
                val liked =
                    if (product.wishlist) R.drawable.ic_heart_checked else R.drawable.ic_heart_unchecked
                like.setImageResource(liked)
            }
            setLike()

            like.setOnClickListener {
                product.wishlist = product.wishlist.not()
                setLike()
                wishlist(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemProductHorizontalSectionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }
}