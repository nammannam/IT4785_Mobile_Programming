package com.example.w7_b2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import com.google.android.material.textfield.*
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.graphics.toColor

class MainActivity : AppCompatActivity() {

    private lateinit var textInputLayoutFName: TextInputLayout
    private lateinit var textInputFName: TextInputEditText

    private lateinit var textInputLNameLayout: TextInputLayout
    private lateinit var textInputLName: TextInputEditText

    private lateinit var textInputBirthdayLayout: TextInputLayout
    private lateinit var textInputBirthday: TextInputEditText

    private lateinit var textInputAddressLayout: TextInputLayout
    private lateinit var textInputAddress: TextInputEditText

    private lateinit var textInputEmailLayout: TextInputLayout
    private lateinit var textInputEmail: TextInputEditText

    private lateinit var btnSelect : Button

    private lateinit var btnRegister : Button

    private lateinit var viewCalendar : CalendarView

    private lateinit var radioBtnMale : RadioButton

    private lateinit var radioBtnFemale : RadioButton

    private lateinit var radioBtnGroup : RadioButton

    private lateinit var chkBoxAgree : CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        // Khoi tao cac TextInputEditText
        textInputLayoutFName = findViewById<TextInputLayout>(R.id.textInputFirstName)
        textInputFName = findViewById<TextInputEditText>(R.id.editTextFirstName)
        textInputLNameLayout = findViewById<TextInputLayout>(R.id.textInputLastName)
        textInputLName = findViewById<TextInputEditText>(R.id.editTextLastName)
        textInputBirthdayLayout = findViewById<TextInputLayout>(R.id.textInputBirthday)
        textInputBirthday = findViewById<TextInputEditText>(R.id.editTextBirthday)
        textInputAddressLayout = findViewById<TextInputLayout>(R.id.textInputAddress)
        textInputAddress = findViewById<TextInputEditText>(R.id.editTextAddress)
        textInputEmailLayout = findViewById<TextInputLayout>(R.id.textInputEmail)
        textInputEmail = findViewById<TextInputEditText>(R.id.editTextEmail)

        radioBtnMale = findViewById<RadioButton>(R.id.checkBoxMale)
        radioBtnFemale = findViewById<RadioButton>(R.id.checkBoxFemale)
        chkBoxAgree = findViewById<CheckBox>(R.id.checkBoxAgree)

        btnSelect = findViewById<Button>(R.id.selectBtn)
        btnRegister = findViewById<Button>(R.id.registerBtn)

        viewCalendar = findViewById<CalendarView>(R.id.calendarView)
        viewCalendar.visibility = CalendarView.GONE

        // Add TextWatcher for last name field
//        textInputLName.addTextChangedListener()

        btnSelect.setOnClickListener {
            if (viewCalendar.visibility == CalendarView.VISIBLE)
                viewCalendar.visibility = CalendarView.GONE
            else
                viewCalendar.visibility = CalendarView.VISIBLE
        }

        viewCalendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val dateString = "${dayOfMonth}/${month + 1}/$year"
            textInputBirthday.setText(dateString)
        }

        btnRegister.setOnClickListener { view ->
            if(textInputLName.text.isNullOrBlank()){
                textInputLName.setBackgroundColor(0xFFE62E2E.toInt())
            }
            if(textInputFName.text.isNullOrBlank()){
                textInputFName.setBackgroundColor(0xFFE62E2E.toInt())
            }
            if(textInputEmail.text.isNullOrBlank()){
                textInputEmail.setBackgroundColor(0xFFE62E2E.toInt())
            }
            if(textInputAddress.text.isNullOrBlank()){
                textInputAddress.setBackgroundColor(0xFFE62E2E.toInt())
            }
            if(textInputBirthday.text.isNullOrBlank()){
                val colorRed = 0xFFE62E2E.toInt()
                textInputBirthday.setBackgroundColor(colorRed)
            }
            if (!textInputEmail.text.isNullOrBlank()){
                val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
                if (!textInputEmail.text.toString().trim().matches(emailPattern.toRegex())){
                    textInputEmailLayout.error = "Invalid email format"
                } else {
                    textInputEmailLayout.error = null
                }
            }
            if(!textInputBirthday.text.isNullOrBlank()){
                val birthdayPattern = "([1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((19|20)\\d\\d)"
                if (!textInputBirthday.text.toString().trim().matches(birthdayPattern.toRegex())){
                    textInputBirthdayLayout.error = "Invalid birthday format dd/mm/yyyy"
                } else {
                    textInputBirthdayLayout.error = null
                }
            }

            if(!radioBtnMale.isChecked() && !radioBtnFemale.isChecked()){
//                Log.d("Register","Please select gender option")
                radioBtnMale.error = "Please select gender option"
                radioBtnFemale.error = "Please select gender option"

            }else{
                radioBtnMale.error = null
                radioBtnFemale.error = null
            }

            if(!chkBoxAgree.isChecked()){
                chkBoxAgree.error = "You must agree to the terms and conditions"
            } else {
                chkBoxAgree.error = null
            }

            if(!textInputFName.text.isNullOrBlank() &&
                !textInputLName.text.isNullOrBlank() &&
                !textInputEmail.text.isNullOrBlank() &&
                !textInputAddress.text.isNullOrBlank() &&
                !textInputBirthday.text.isNullOrBlank()){

                textInputFName.setBackgroundColor(0xFF1CE83D.toInt())
                textInputLName.setBackgroundColor(0xFF1CE83D.toInt())
                textInputEmail.setBackgroundColor(0xFF1CE83D.toInt())
                textInputAddress.setBackgroundColor(0xFF1CE83D.toInt())
                textInputBirthday.setBackgroundColor(0xFF1CE83D.toInt())

            }



        }




//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }





}