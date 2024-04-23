package com.oybekdev.e_commerce.presentation.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.data.api.product.dto.Category
import com.oybekdev.e_commerce.databinding.ItemCategoryBinding

class CategoriesAdapter(
    private val categories: List<Category>,
    private val onClick: (category: Category) -> Unit,
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(category: Category) = with(binding){
                Glide.with(root).load(category.image).into(image)
                title.text = category.title
                count.text = root.context.getString(R.string.item_category_count,category.count)
                root.setOnClickListener{
                    onClick(category)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }
}