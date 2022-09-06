package com.example.battlemanager.presentation.dialog.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.battlemanager.databinding.ItemFilterBinding
import com.example.battlemanager.domain.model.FilterItem

class FilterAdapter(private val items: List<FilterItem>) : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, listener)
    }
    interface OnFilterItemClickListener{
        fun onItemClick(view : View, pos : Int)
    }

    lateinit var listener : OnFilterItemClickListener

    fun setOnFilterItemClickListener(listener : OnFilterItemClickListener){
        this.listener = listener
    }


    class FilterViewHolder private constructor(
        val binding: ItemFilterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FilterItem, listener: OnFilterItemClickListener) {
            binding.item = item

            itemView.setOnClickListener { v ->
                val pos = adapterPosition
                listener.onItemClick(v!!, pos)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FilterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFilterBinding.inflate(layoutInflater, parent, false)

                return FilterViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun getItem(pos : Int) : FilterItem{
        return items[pos]
    }
}