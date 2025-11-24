package com.example.w9_b3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class SuggestedAppAdapter(
    private val context: Context,
    private val apps: List<App>
) : BaseAdapter() {

    override fun getCount(): Int = apps.size

    override fun getItem(position: Int): Any = apps[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_suggested_app,
            parent,
            false
        )

        val app = apps[position]

        val imgAppIcon = view.findViewById<ImageView>(R.id.imgAppIcon)
        val txtAppName = view.findViewById<TextView>(R.id.txtAppName)
        val txtAppCategory = view.findViewById<TextView>(R.id.txtAppCategory)
        val txtAppRating = view.findViewById<TextView>(R.id.txtAppRating)
        val txtAppSize = view.findViewById<TextView>(R.id.txtAppSize)

        imgAppIcon.setImageResource(app.iconResId)
        txtAppName.text = app.name
        txtAppCategory.text = app.category
        txtAppRating.text = app.rating.toString()
        txtAppSize.text = app.size

        return view
    }
}

