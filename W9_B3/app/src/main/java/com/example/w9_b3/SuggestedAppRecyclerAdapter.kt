package com.example.w9_b3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SuggestedAppRecyclerAdapter(
    private val apps: List<App>,
    private val onItemClick: (App) -> Unit
) : RecyclerView.Adapter<SuggestedAppRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgAppIcon: ImageView = view.findViewById(R.id.imgAppIcon)
        val txtAppName: TextView = view.findViewById(R.id.txtAppName)
        val txtAppCategory: TextView = view.findViewById(R.id.txtAppCategory)
        val txtAppRating: TextView = view.findViewById(R.id.txtAppRating)
        val txtAppSize: TextView = view.findViewById(R.id.txtAppSize)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_suggested_app, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = apps[position]
        holder.imgAppIcon.setImageResource(app.iconResId)
        holder.txtAppName.text = app.name
        holder.txtAppCategory.text = app.category
        holder.txtAppRating.text = app.rating.toString()
        holder.txtAppSize.text = app.size

        holder.itemView.setOnClickListener {
            onItemClick(app)
        }
    }

    override fun getItemCount() = apps.size
}

