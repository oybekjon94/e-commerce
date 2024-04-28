package com.oybekdev.e_commerce.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oybekdev.e_commerce.data.api.product.dto.Product
import com.oybekdev.e_commerce.data.api.product.dto.Section
import com.oybekdev.e_commerce.data.api.product.dto.SectionType
import com.oybekdev.e_commerce.databinding.ItemSectionHorizontalBinding
import com.oybekdev.e_commerce.databinding.ItemSectionVerticalBinding

class SectionAdapter(
    private val sections:List<Section>,
    private val showAll:(section:Section) -> Unit,
    private val onClick: (product:Product) -> Unit,
    private val wishlist: (product:Product) -> Unit
) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    inner class HorizontalHolder(private val binding:ItemSectionHorizontalBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(section: Section) = with(binding){
            title.text  =section.title
            showAll.setOnClickListener {
                this@SectionAdapter.showAll(section)
            }
            products.adapter = HorizontalAdapter(section.products,onClick,wishlist)
        }
    }
    inner class VerticalHolder(private val binding:ItemSectionVerticalBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(section: Section) = with(binding){
            title.text = section.title
            showAll.setOnClickListener {
                this@SectionAdapter.showAll(section)
            }
            products.adapter = VerticalAdapter(section.products,onClick,wishlist)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(SectionType.values()[viewType]){
            SectionType.horizontal -> HorizontalHolder(ItemSectionHorizontalBinding.inflate(inflater,parent,false))
            SectionType.vertical -> VerticalHolder(ItemSectionVerticalBinding.inflate(inflater,parent,false))
        }
    }

    override fun getItemCount() = sections.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HorizontalHolder -> holder.bind(sections[position])
            is VerticalHolder -> holder.bind(sections[position])
        }
    }

    override fun getItemViewType(position:Int) = sections[position].type.ordinal
}