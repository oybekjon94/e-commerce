package com.oybekdev.e_commerce.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oybekdev.e_commerce.data.api.product.dto.Banner
import com.oybekdev.e_commerce.databinding.ItemBannerBinding
import com.oybekdev.e_commerce.databinding.ItemDetailImageBinding

class ImageAdapter(
    private val images: List<String>,
) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemDetailImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String) = with(binding) {
            Glide.with(root).load(image).into(binding.image)
            root.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemDetailImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = images.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }
}