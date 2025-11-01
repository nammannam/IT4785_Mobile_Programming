package com.example.w7_b1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.view.View

class MainActivity : ComponentActivity() {

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

    // Biến lưu trạng thái
    private var currentNumber = ""
    private var previousNumber = ""
    private var operator = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.caculator_layout)

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

        // Thiết lập listener cho các nút số
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

        // Hiển thị giá trị ban đầu
        dispNum.text = "0"
    }

    private fun onNumberClick(number: String) {
        if (isNewOperation) {
            currentNumber = number
            isNewOperation = false
        } else {
            currentNumber += number
        }
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
        } catch (e: Exception) {
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