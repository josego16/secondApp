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

    private fun validarDatos(datos: String): Boolean {
        val regex = Regex("^[a-zA-Z][0-9a-zA-z]+$")
        return datos.matches(regex)
    }

    private fun initEvent() {
        botonesBinding.idBotonSiguiente.setOnClickListener {
            val dateSelected = botonesBinding.idEditCalendar.text.toString()
            val nameSelected = botonesBinding.idEditNombre.text.toString()
            val tiradasSelected = obtenerTiradas()
            val segundosSelected = obtenerSegundos()

            if (validarDatos(nameSelected)) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("DATE_SELECTED", dateSelected)
                intent.putExtra("NAME_SELECTED", nameSelected)
                intent.putExtra("TIRADAS_SELECTED", tiradasSelected)
                intent.putExtra("SEGUNDOS_SELECTED", segundosSelected)
                startActivity(intent)
            } else {
                botonesBinding.idBotonSiguiente.error = "Nombre incorrecto"
            }
        }
        botonesBinding.idEditCalendar.setOnClickListener { showDatePickerDialog() }
        botonesBinding.idSpinnerTiempo.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun obtenerTiradas(): Int {
        return when (botonesBinding.idGroupTiradas.checkedRadioButtonId) {
            botonesBinding.idRadio1.id -> 1
            botonesBinding.idRadio2.id -> 2
            botonesBinding.idRadio3.id -> 3
            botonesBinding.idRadio4.id -> 4
            botonesBinding.idRadio5.id -> 5
            else -> 0
        }
    }

    private fun obtenerSegundos(): Int {
        val tiempoSeleccionado = botonesBinding.idSpinnerTiempo.selectedItem.toString()
        return tiempoSeleccionado.substringBefore("seg").toInt()
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