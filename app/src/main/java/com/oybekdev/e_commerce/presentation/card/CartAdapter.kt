package com.oybekdev.e_commerce.presentation.card

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.databinding.ItemCartBinding
import com.oybekdev.e_commerce.domain.model.Cart
import com.oybekdev.e_commerce.util.dp

class CartAdapter(
    private val carts: List<Cart>,
    private val onClick: (cart: Cart) -> Unit,
    private val set: (cart: Cart) -> Unit,
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cart: Cart) = with(binding) {
            Glide.with(root).load(cart.image).transform(CenterCrop(),RoundedCorners(14.dp)).into(image)
            title.text = cart.title
            category.text = cart.category
            price.text = root.context.getString(R.string.price, cart.price - (cart.discount ?: 0.0))
            old.text = root.context.getString(R.string.price_striked, cart.price)
            old.isVisible = cart.discount != null
            count.text = cart.count.toString()
            root.setOnClickListener {
                onClick(cart)
            }
            plus.setOnClickListener {
                cart.count++
                count.text = cart.count.toString()
            }
            minus.setOnClickListener {
                cart.count--
                count.text = cart.count.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = carts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(carts[position])
    }

}