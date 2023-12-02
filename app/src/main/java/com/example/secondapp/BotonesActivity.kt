package com.example.secondapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.secondapp.databinding.ActivityBotonesBinding

class BotonesActivity : AppCompatActivity() {
    private lateinit var botonesBinding: ActivityBotonesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        botonesBinding = ActivityBotonesBinding.inflate(layoutInflater)
        setContentView(botonesBinding.root)
        /*setContentView(R.layout.activity_botones)*/
        botonesBinding.idEditCalendar.setOnClickListener { showDatePickerDialog() }

        initEvent()
    }

    private fun initEvent() {
        botonesBinding.idBotonSiguiente.setOnClickListener {
            val dateSelected = botonesBinding.idEditCalendar.text.toString()
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("DATE_SELECTED", dateSelected)
            }
            startActivity(intent)
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val dateSelected = "Has seleccionado el $day del $month del año $year"
        botonesBinding.idEditCalendar.setText(dateSelected)
        //Toast.makeText(this, dateSelected, Toast.LENGTH_SHORT).show()
    }
}