package com.example.w9_b2

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(val items: List<ItemModel>, val action: (Int)-> Unit) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = android.view.LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_gmail, parent, false)
        return ItemViewHolder(itemView, action)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.image.setImageResource(items[position].imageResource)
        holder.title.text = items[position].title
        holder.content.text = items[position].content
        holder.time.text = items[position].time
    }

    override fun getItemCount() = items.size

    class ItemViewHolder(itemView: View, val action: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val image: android.widget.ImageView = itemView.findViewById(R.id.imageView)
        val title: android.widget.TextView = itemView.findViewById(R.id.titleTextView)
        val content: android.widget.TextView = itemView.findViewById(R.id.contentTextView)
        val time: android.widget.TextView = itemView.findViewById(R.id.timeTextView)

        init {
            itemView.setOnClickListener {
                action(bindingAdapterPosition)

            }
        }

    }
}