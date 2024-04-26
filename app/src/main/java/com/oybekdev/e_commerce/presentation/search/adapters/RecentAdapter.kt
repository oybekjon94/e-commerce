package com.oybekdev.e_commerce.presentation.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oybekdev.e_commerce.databinding.ItemRecentBinding

class RecentAdapter(
    private val recents: List<String>,
    private val onClick: (search: String) -> Unit,
) : RecyclerView.Adapter<RecentAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRecentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(search: String) {
            binding.search.text = search
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        ItemRecentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = recents.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recents[position])
    }
}