package com.example.w8_b2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var textInput : EditText

    lateinit var radioBtnOdd : RadioButton

    lateinit var radioBtnEven : RadioButton

    lateinit var radioBtnPrime : RadioButton

    lateinit var radioBtnPfNum : RadioButton

    lateinit var radioBtnFibo : RadioButton

    lateinit var radioBtnSqNum: RadioButton

    lateinit var radioGroup: RadioGroup

    lateinit var listView : ListView

    lateinit var textViewMessage : TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textInput = findViewById<EditText>(R.id.editText)

        radioBtnOdd     = findViewById<RadioButton>(R.id.radioBtnOdd)
        radioBtnEven    = findViewById<RadioButton>(R.id.radioBtnEven)
        radioBtnPrime   = findViewById<RadioButton>(R.id.radioBtnPrime)
        radioBtnPfNum   = findViewById<RadioButton>(R.id.radioBtnPfNum)
        radioBtnFibo    = findViewById<RadioButton>(R.id.radioBtnFibonacci)
        radioBtnSqNum   = findViewById<RadioButton>(R.id.radioBtnSqNum)

        radioGroup      = findViewById<RadioGroup>(R.id.radioGroup)
        listView        = findViewById<ListView>(R.id.listView)
        textViewMessage = findViewById<TextView>(R.id.textViewMessage)

        // Thêm listener cho RadioGroup để cập nhật khi chọn radio button
        radioGroup.setOnCheckedChangeListener { _, _ ->
            updateListView()
        }

        // Thêm TextWatcher cho EditText để cập nhật khi thay đổi số
        textInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                // Cập nhật ListView khi người dùng đã nhập xong
                updateListView()
            }
        })
    }

    fun updateListView() {
        try {
            val n = textInput.text.toString().toIntOrNull()

            if (n == null || n <= 0) {
                // Nếu input không hợp lệ, ẩn cả hai
                listView.visibility = View.GONE
                textViewMessage.visibility = View.GONE
                return
            }

            var listNumber: MutableList<String> = mutableListOf()

            if (radioBtnOdd.isChecked) {
                listNumber = printOddNumbers(n)

            } else if (radioBtnEven.isChecked) {
                listNumber = printEvenNumbers(n)

            } else if (radioBtnFibo.isChecked) {
                listNumber = printFibonacciNumbers(n)

            } else if (radioBtnPfNum.isChecked) {
                listNumber = printPerfectNumbers(n)

            } else if (radioBtnPrime.isChecked) {
                listNumber = printPrimeNumbers(n)

            } else if (radioBtnSqNum.isChecked) {
                listNumber = printSquareNumbers(n)
            } else {
                // Chưa chọn radio button nào
                listView.visibility = View.GONE
                textViewMessage.visibility = View.GONE
                return
            }

            // Kiểm tra xem có kết quả không
            if (listNumber.isEmpty()) {
                // Không có số nào thỏa mãn - hiển thị thông báo
                listView.visibility = View.GONE
                textViewMessage.visibility = View.VISIBLE
            } else {
                // Có kết quả - hiển thị ListView
                textViewMessage.visibility = View.GONE
                listView.visibility = View.VISIBLE

                val adapter: ArrayAdapter<String> = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    listNumber
                )
                listView.adapter = adapter
            }

        } catch (e: Exception) {
            // Xử lý lỗi nếu có
            listView.visibility = View.GONE
            textViewMessage.visibility = View.GONE
        }
    }

    fun printPrimeNumbers(n : Int) : MutableList<String>{
        val primeNumbers = mutableListOf<String>()
        for(num in 2..n){
            var isPrime = true
            for(i in 2 until num){
                if(num % i == 0){
                    isPrime = false
                    break
                }
            }
            if(isPrime){
                primeNumbers.add(num.toString())
            }
        }
        return primeNumbers

    }

    fun printPerfectNumbers(n : Int) : MutableList<String>{
        val perfectNumbers = mutableListOf<String>()
        for(num in 2..n){
            var sumOfDivisors = 0
            for(i in 1 until num){
                if(num % i == 0){
                    sumOfDivisors += i
                }
            }
            if(sumOfDivisors == num){
                perfectNumbers.add(num.toString())
            }
        }
        return perfectNumbers

    }

    fun printFibonacciNumbers(n : Int) : MutableList<String>{
        val fibonacciNumbers = mutableListOf<String>()
        var a = 0
        var b = 1
        while(a < n){
            fibonacciNumbers.add(a.toString())
            val next = a + b
            a = b
            b = next
        }
        return fibonacciNumbers
    }

    fun printSquareNumbers(n : Int) : MutableList<String>{
        val squareNumbers = mutableListOf<String>()
        var i = 1
        while(i * i <= n){
            squareNumbers.add((i * i).toString())
            i++
        }

        return squareNumbers

    }

    fun printOddNumbers(n : Int): MutableList<String> {
        val oddNumbers = mutableListOf<String>()
        for(num in 1..n step 2){
            oddNumbers.add(num.toString())
        }
        return oddNumbers

    }

    fun printEvenNumbers(n : Int) : MutableList<String>{
        val evenNumbers = mutableListOf<String>()
        for(num in 2..n step 2){
            evenNumbers.add(num.toString())
        }
        return evenNumbers

    }


}