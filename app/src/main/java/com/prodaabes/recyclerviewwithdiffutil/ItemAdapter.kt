package com.prodaabes.recyclerviewwithdiffutil

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.pavolive.recyclerviewwithdiffutil.databinding.ListItemBinding

class ItemAdapter(var context: Context, var itemClickListener: OnItemClickListener): ListAdapter<Item, ItemAdapter.ItemViewHolder>(
    ItemCallback()
) {

    class ItemCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    class ItemViewHolder(val binding: ListItemBinding, var itemClickListener: OnItemClickListener): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnLongClickListener { p0 ->
                itemClickListener.onItemLongClick(adapterPosition)
                return@setOnLongClickListener false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = currentList[position]

        Glide.with(context)
            .load(item.imageURL)
            .into(holder.binding.imageView)

        holder.binding.tvBrand.text = item.brand
        holder.binding.tvModel.text = item.model
    }

    interface OnItemClickListener {
        fun onItemLongClick(position: Int)
    }
}