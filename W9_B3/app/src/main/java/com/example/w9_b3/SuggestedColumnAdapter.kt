package com.example.w9_b3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SuggestedColumnAdapter(
    private val columns: List<List<App>>,
    private val onItemClick: (App) -> Unit
) : RecyclerView.Adapter<SuggestedColumnAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val columnContainer: LinearLayout = view.findViewById(R.id.columnContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_suggested_column, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val column = columns[position]
        holder.columnContainer.removeAllViews()

        val inflater = LayoutInflater.from(holder.itemView.context)

        // Thêm từng app vào cột
        for (app in column) {
            val itemView = inflater.inflate(R.layout.item_suggested_app, holder.columnContainer, false)

            val imgAppIcon = itemView.findViewById<ImageView>(R.id.imgAppIcon)
            val txtAppName = itemView.findViewById<TextView>(R.id.txtAppName)
            val txtAppCategory = itemView.findViewById<TextView>(R.id.txtAppCategory)
            val txtAppRating = itemView.findViewById<TextView>(R.id.txtAppRating)
            val txtAppSize = itemView.findViewById<TextView>(R.id.txtAppSize)

            imgAppIcon.setImageResource(app.iconResId)
            txtAppName.text = app.name
            txtAppCategory.text = app.category
            txtAppRating.text = app.rating.toString()
            txtAppSize.text = app.size

            itemView.setOnClickListener {
                onItemClick(app)
            }

            holder.columnContainer.addView(itemView)
        }
    }

    override fun getItemCount() = columns.size
}

