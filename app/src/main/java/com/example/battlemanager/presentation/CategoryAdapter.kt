package com.example.battlemanager.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.battlemanager.databinding.ItemCategoryBinding
import com.example.battlemanager.domain.model.CategoryItem

class CategoryAdapter(private val items: List<CategoryItem>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, listener)
    }

    interface OnCategoryItemClickListener {
        fun onItemClick(view: View, pos: Int)
    }

    lateinit var listener: OnCategoryItemClickListener

    fun setOnCategoryItemClickListener(listener: OnCategoryItemClickListener) {
        this.listener = listener
    }


    inner class CategoryViewHolder(
        val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryItem, listener: OnCategoryItemClickListener) {
            binding.item = item

            item.isSelected = selectedPosition == adapterPosition

            itemView.setOnClickListener { v ->
                if (selectedPosition != adapterPosition) {
                    notifyItemChanged(selectedPosition)
                    selectedPosition = adapterPosition
                    listener.onItemClick(v!!, adapterPosition)
                }
            }
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItem(pos: Int): CategoryItem {
        return items[pos]
    }
}