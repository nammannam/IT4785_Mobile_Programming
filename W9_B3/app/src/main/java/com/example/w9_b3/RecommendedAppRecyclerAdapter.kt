package com.example.w9_b3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecommendedAppRecyclerAdapter(
    private val apps: List<App>,
    private val onItemClick: (App) -> Unit
) : RecyclerView.Adapter<RecommendedAppRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgRecommendedIcon: ImageView = view.findViewById(R.id.imgRecommendedIcon)
        val txtRecommendedName: TextView = view.findViewById(R.id.txtRecommendedName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommended_app, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = apps[position]
        holder.imgRecommendedIcon.setImageResource(app.iconResId)
        holder.txtRecommendedName.text = app.name

        holder.itemView.setOnClickListener {
            onItemClick(app)
        }
    }

    override fun getItemCount() = apps.size
}

