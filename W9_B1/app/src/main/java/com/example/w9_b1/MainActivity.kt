package com.example.w9_b1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.AdapterView


class MainActivity : AppCompatActivity() {
    private lateinit var mssvInput: EditText
    private lateinit var nameInput: EditText
    private lateinit var addBtn: Button
    private lateinit var updateBtn: Button
    private lateinit var listView: ListView

    private val students = mutableListOf<Student>()
    private lateinit var adapter: StudentAdapter
    private var selectedPosition: Int = -1

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        mssvInput = findViewById(R.id.mssvInput)
        nameInput = findViewById(R.id.nameInput)
        addBtn = findViewById(R.id.addBtn)
        updateBtn = findViewById(R.id.updateBtn)
        listView = findViewById(R.id.listView)

        // Setup adapter
        adapter = StudentAdapter(this, students) { position ->
            deleteStudent(position)
        }
        listView.adapter = adapter

        // Add button click listener
        addBtn.setOnClickListener {
            addStudent()
        }

        // Update button click listener
        updateBtn.setOnClickListener {
            updateStudent()
        }

        // ListView item click listener
        listView.setOnItemClickListener { parent, view, position, id ->
            Log.d(TAG, "========================================")
            Log.d(TAG, "Item clicked at position: $position")
            val student = students[position]
            Log.d(TAG, "Selected student - Name: ${student.name}, MSSV: ${student.mssv}")
            Log.d(TAG, "========================================")
            selectStudent(position)
        }
    }


    private fun addStudent() {
        val mssv = mssvInput.text.toString().trim()
        val name = nameInput.text.toString().trim()

        if (mssv.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        val student = Student(mssv, name)
        students.add(student)
        adapter.notifyDataSetChanged()

        Log.d(TAG, "Student added - Name: $name, MSSV: $mssv, Total students: ${students.size}")

        // Clear input fields
        clearInputs()
        Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show()
    }

    private fun deleteStudent(position: Int) {
        val student = students[position]
        Log.d(TAG, "Deleting student at position $position - Name: ${student.name}, MSSV: ${student.mssv}")

        students.removeAt(position)
        adapter.notifyDataSetChanged()

        // Clear selection if the deleted item was selected
        if (selectedPosition == position) {
            selectedPosition = -1
            clearInputs()
        } else if (selectedPosition > position) {
            selectedPosition--
        }

        Log.d(TAG, "Student deleted. Remaining students: ${students.size}")
        Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show()
    }

    private fun selectStudent(position: Int) {
        selectedPosition = position
        val student = students[position]
        mssvInput.setText(student.mssv)
        nameInput.setText(student.name)
        Log.d(TAG, "Student selected for editing at position: $position")
    }

    private fun updateStudent() {
        if (selectedPosition == -1) {
            Toast.makeText(this, "Vui lòng chọn sinh viên để cập nhật", Toast.LENGTH_SHORT).show()
            return
        }

        val mssv = mssvInput.text.toString().trim()
        val name = nameInput.text.toString().trim()

        if (mssv.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        val oldStudent = students[selectedPosition]
        Log.d(TAG, "Updating student at position $selectedPosition")
        Log.d(TAG, "Old values - Name: ${oldStudent.name}, MSSV: ${oldStudent.mssv}")
        Log.d(TAG, "New values - Name: $name, MSSV: $mssv")

        students[selectedPosition].mssv = mssv
        students[selectedPosition].name = name
        adapter.notifyDataSetChanged()

        clearInputs()
        selectedPosition = -1
        Toast.makeText(this, "Đã cập nhật sinh viên", Toast.LENGTH_SHORT).show()
    }

    private fun clearInputs() {
        mssvInput.text?.clear()
        nameInput.text?.clear()
    }
}