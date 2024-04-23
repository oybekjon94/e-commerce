package com.oybekdev.e_commerce.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oybekdev.e_commerce.data.api.product.dto.Banner
import com.oybekdev.e_commerce.databinding.ItemBannerBinding

class BannerAdapter(private val banners:List<Banner>, private val onClick:(banner:Banner) -> Unit):RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding:ItemBannerBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(banner: Banner) = with(binding){
            Glide.with(root).load(banner.image).into(image)
            root.setOnClickListener{
                onClick(banner)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int) = ViewHolder(
        ItemBannerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = banners.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(banners[position])
    }
}