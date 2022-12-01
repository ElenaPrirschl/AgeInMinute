package com.example.ageperminute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeinMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeinMinutes = findViewById(R.id.minutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()

        }
    }

    private fun clickDatePicker(){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
            _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            tvSelectedDate?.text =selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            theDate?.let {

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDateInMinutes = currentDate.time / 60000
                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                    tvAgeinMinutes?.text = differenceInMinutes.toString()
                }
            }

        },
            year, month, day)

        dpd.datePicker.maxDate = System.currentTimeMillis() -86400000
        dpd.show()
    }

}