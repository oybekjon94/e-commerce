package com.oybekdev.e_commerce.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oybekdev.e_commerce.R
import com.oybekdev.e_commerce.data.api.product.dto.Category
import com.oybekdev.e_commerce.databinding.ItemCategoryHomeBinding

class HomeCategoryAdapter(
    private val categories:List<Category>,
    //A lambda function that will be invoked when a category item is clicked.
    private val onClick:(category:Category) -> Unit
): RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder>() {

    /*
    ViewHolder is responsible for caching and managing the view references for each item in
    the RecyclerView.It binds the Category data to the corresponding views within the item layout.
     */
    inner class ViewHolder(private val binding: ItemCategoryHomeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category) = with(binding){
            Glide.with(root).load(category.image).into(image)
            name.text = category.title
            items.text = root.context.getString(R.string.item_category_count,category.count)
            root.setOnClickListener{
                onClick(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ItemCategoryHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }
}