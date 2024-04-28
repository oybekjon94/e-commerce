package com.oybekdev.e_commerce.presentation.products

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.databinding.ItemProductBinding
import kotlin.math.roundToInt

class ProductViewHolder(private val binding: ItemProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        product: Product,
        onClick: (product: Product) -> Unit,
        wishlist: (product: Product) -> Unit,
    ) = with(binding) {
        Glide.with(root).load(product.image).into(image)

        discount.isVisible = product.discount != null
        product.discount?.let {
            val discount = (product.discount / product.price * 100).roundToInt()
            binding.discount.text =
                root.context.getString(R.string.fragment_item_product_discount, discount)
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
            binding.like.setImageResource(liked)
        }
        setLike()

        like.setOnClickListener {
            product.wishlist = product.wishlist.not()
            setLike()
            wishlist(product)
        }
    }
}