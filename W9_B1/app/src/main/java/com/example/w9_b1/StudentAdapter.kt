package com.example.w9_b1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView

class StudentAdapter(
    private val context: Context,
    private val students: MutableList<Student>,
    private val onDeleteClick: (Int) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = students.size

    override fun getItem(position: Int): Any = students[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_sv_view, parent, false)

        val student = students[position]
        val nameTextView = view.findViewById<TextView>(R.id.textView)
        val mssvTextView = view.findViewById<TextView>(R.id.textView2)
        val deleteBtn = view.findViewById<ImageButton>(R.id.deleteBtn)

        nameTextView.text = student.name
        mssvTextView.text = student.mssv

        // Handle delete button click
        deleteBtn.setOnClickListener {
            onDeleteClick(position)
        }

        return view
    }
}
