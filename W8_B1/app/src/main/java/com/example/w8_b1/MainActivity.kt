package com.example.w8_b1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var dispNum : TextView
    lateinit var btn0 : Button
    lateinit var btn1 : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    lateinit var btn4 : Button
    lateinit var btn5 : Button
    lateinit var btn6 : Button
    lateinit var btn7 : Button
    lateinit var btn8 : Button
    lateinit var btn9 : Button
    lateinit var btnC : Button
    lateinit var btnEqual : Button
    lateinit var btnPlus : Button
    lateinit var btnMinus : Button
    lateinit var btnMul : Button
    lateinit var btnDiv : Button
    lateinit var btnDot : Button
    lateinit var btnCE : Button
    lateinit var btnBS : Button
    lateinit var btnSign : Button
    lateinit var btnConvert: Button

    // Biến lưu trạng thái
    private var currentNumber = ""
    private var previousNumber = ""
    private var operator = ""
    private var isNewOperation = true

    private var isNewConversion = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //
        val currencies : Array<String> = arrayOf("VND", "USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "HKD")

        val adapter : ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            currencies
        )
        val spinnerFirstCur : Spinner = findViewById(R.id.spinnerFirstCur)
        spinnerFirstCur.run{
            this.adapter = adapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0 : AdapterView<*>?, p1: View?, p2 : Int, p3 : Long) {
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        }

        val spinnerSecondCur: Spinner = findViewById(R.id.spinnerSecondCur)
        spinnerSecondCur.run {
            this.adapter = adapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }



        // Khởi tạo các view
        dispNum = findViewById(R.id.dispNum)
        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btnC = findViewById(R.id.btnC)
        btnCE = findViewById(R.id.btnCE)
        btnBS = findViewById(R.id.btnBS)
        btnEqual = findViewById(R.id.btnEqual)
        btnPlus = findViewById(R.id.btnAdd)
        btnMinus = findViewById(R.id.btnSub)
        btnMul = findViewById(R.id.btnMult)
        btnDiv = findViewById(R.id.btnDiv)
        btnDot = findViewById(R.id.btnDot)
        btnSign = findViewById(R.id.btnSign)
        btnConvert = findViewById(R.id.btnConvert)

        // Thiết lập listener cho các nút số - lambda funct
        val numberClickListener = View.OnClickListener { view ->
            val button = view as Button
            onNumberClick(button.text.toString())
        }

        btn0.setOnClickListener(numberClickListener)
        btn1.setOnClickListener(numberClickListener)
        btn2.setOnClickListener(numberClickListener)
        btn3.setOnClickListener(numberClickListener)
        btn4.setOnClickListener(numberClickListener)
        btn5.setOnClickListener(numberClickListener)
        btn6.setOnClickListener(numberClickListener)
        btn7.setOnClickListener(numberClickListener)
        btn8.setOnClickListener(numberClickListener)
        btn9.setOnClickListener(numberClickListener)

        // Thiết lập listener cho các phép toán
        btnPlus.setOnClickListener { onOperatorClick("+") }
        btnMinus.setOnClickListener { onOperatorClick("-") }
        btnMul.setOnClickListener { onOperatorClick("×") }
        btnDiv.setOnClickListener { onOperatorClick("/") }

        // Thiết lập listener cho các nút chức năng
        btnEqual.setOnClickListener { onEqualClick() }
        btnC.setOnClickListener { onClearClick() }
        btnCE.setOnClickListener { onClearEntryClick() }
        btnBS.setOnClickListener { onBackspaceClick() }
        btnDot.setOnClickListener { onDotClick() }
        btnSign.setOnClickListener { onSignClick() }

        // Convert button listener: dùng spinner để lấy đơn vị và chuyển đổi
        btnConvert.setOnClickListener {
            if(isNewConversion) {
                val from = spinnerFirstCur.selectedItem as String
                val to = spinnerSecondCur.selectedItem as String
                performCurrencyConversion(from, to)

            }
        }

        // Hiển thị giá trị ban đầu
        dispNum.text = "0"


    }

    // Tỷ giá (cố định, tham chiếu theo USD) 1 USD = X (don vi tien)
    private val ratesToUSD = mapOf(
        "USD" to 1.0,
        // approximate sample rates; you can update as needed
        "VND" to 0.000038,
        "EUR" to 1.149425,
        "JPY" to 0.0065,
        "GBP" to 1.298701,
        "AUD" to 0.64,
        "CAD" to 0.74,
        "CHF" to 1.10,
        "CNY" to 0.14,
        "HKD" to 0.13
    )

    private fun performCurrencyConversion(from: String, to: String) {
        val input =
            if (currentNumber.isNotEmpty() && !isNewOperation)
                currentNumber
            else
                dispNum.text.toString()

        val amount = try {
            input.replace(" ", "").toDouble()
        } catch (_: Exception) {
            dispNum.text = "Error"
            return
        }

        val fromRate = ratesToUSD[from]
        val toRate = ratesToUSD[to]
        if (fromRate == null || toRate == null) {
            dispNum.text = "Unsupported"
            return
        }

        // Convert: amount (from) -> USD -> target
        val amountInUSD = amount * fromRate
        val converted = if (toRate != 0.0) amountInUSD / toRate else {
            dispNum.text = "Error"
            return
        }

        // Format result: remove trailing .0 when integer
        currentNumber = if (converted % 1.0 == 0.0) converted.toDouble().toString() else String.format(java.util.Locale.US, "%.4f", converted).trimEnd('0').trimEnd('.')
        isNewOperation = true
        isNewConversion = false
        updateDisplay()
    }

    private fun onNumberClick(number: String) {
        if (isNewOperation) {
            currentNumber = number
            isNewOperation = false
        } else {
            currentNumber += number
        }
        isNewConversion = true
        updateDisplay()
    }

    private fun onOperatorClick(op: String) {
        if (currentNumber.isNotEmpty()) {
            if (previousNumber.isNotEmpty() && operator.isNotEmpty() && !isNewOperation) {
                calculateResult()
            }
            previousNumber = currentNumber
            operator = op
            isNewOperation = true
        } else if (previousNumber.isNotEmpty()) {
            operator = op
        }
    }

    private fun onEqualClick() {
        if (previousNumber.isNotEmpty() && currentNumber.isNotEmpty() && operator.isNotEmpty()) {
            calculateResult()
            operator = ""
            previousNumber = ""
        }
    }

    private fun calculateResult() {
        try {
            val num1 = previousNumber.toDouble()
            val num2 = currentNumber.toDouble()
            val result = when (operator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "×" -> num1 * num2
                "/" -> {
                    if (num2 != 0.0) num1 / num2
                    else {
                        // Xử lý lỗi chia cho 0
                        dispNum.text = "Error"
                        // Reset trạng thái
                        currentNumber = ""
                        previousNumber = ""
                        operator = ""
                        isNewOperation = true
                        return
                    }
                }
                else -> return
            }

            // Định dạng kết quả
            currentNumber = if (result % 1.0 == 0.0) {
                result.toLong().toString()
            } else {
                result.toString()
            }
            isNewOperation = true
            updateDisplay()
        } catch (_: Exception) {
            dispNum.text = "Error"
            currentNumber = ""
            previousNumber = ""
            operator = ""
            isNewOperation = true
        }
    }

    private fun onClearClick() {
        currentNumber = ""
        previousNumber = ""
        operator = ""
        isNewOperation = true
        dispNum.text = "0"
    }

    private fun onClearEntryClick() {
        currentNumber = ""
        isNewOperation = true
        dispNum.text = "0"
    }

    private fun onBackspaceClick() {
        if (currentNumber.isNotEmpty() && !isNewOperation) {
            currentNumber = currentNumber.dropLast(1)
            updateDisplay()
        }
    }

    private fun onDotClick() {
        if (isNewOperation) {
            currentNumber = "0."
            isNewOperation = false
        } else if (!currentNumber.contains(".")) {
            currentNumber += "."
        }
        updateDisplay()
    }

    private fun onSignClick() {
        if (currentNumber.isNotEmpty() && currentNumber != "0") {
            currentNumber =
                if (currentNumber.startsWith("-")) {
                    currentNumber.substring(1)

                } else {
                    "-$currentNumber"

                }
            updateDisplay()
        }
    }

    private fun updateDisplay() {
        dispNum.text = if (currentNumber.isEmpty()) "0" else currentNumber
    }



}