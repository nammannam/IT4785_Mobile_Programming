package com.example.w9_b2

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val image : ImageView = itemView.findViewById(R.id.imageView)
    val title : TextView = itemView.findViewById(R.id.titleTextView)
    val content: TextView = itemView.findViewById(R.id.contentTextView)
    val time: TextView = itemView.findViewById(R.id.timeTextView)

}