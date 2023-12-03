package com.example.secondapp

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.secondapp.databinding.ActivityBotonesBinding

class BotonesActivity : AppCompatActivity() {
    private lateinit var botonesBinding: ActivityBotonesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        botonesBinding = ActivityBotonesBinding.inflate(layoutInflater)
        setContentView(botonesBinding.root)
        initEvent()

        val listaTiempos = arrayOf("1seg", "2seg", "3seg", "4seg", "5seg")
        val adapter = arrayAdapter(listaTiempos)
        botonesBinding.idSpinnerTiempo.adapter = adapter
    }

    private fun arrayAdapter(listaTiempos: Array<String>): ArrayAdapter<String> {
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, listaTiempos)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        return adapter
    }

    private fun initEvent() {
        botonesBinding.idBotonSiguiente.setOnClickListener {
            val dateSelected = botonesBinding.idEditCalendar.text.toString()
            val nameSelected = botonesBinding.idEditNombre.text.toString()

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("DATE_SELECTED", dateSelected)
                putExtra("NAME SELECTED", nameSelected)
            }
            startActivity(intent)
        }
        botonesBinding.idEditCalendar.setOnClickListener { showDatePickerDialog() }
        botonesBinding.idSpinnerTiempo.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long) {
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val dateSelected = "$day del $month del año $year"
        botonesBinding.idEditCalendar.setText(dateSelected)
    }
}