package com.example.secondapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import androidx.appcompat.app.AppCompatActivity
import com.example.secondapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initEvent()
        peticionDatos()
    }

    private fun peticionDatos() {
        val dateSelected = intent.getStringExtra("DATE_SELECTED")
        mainBinding.idTextFechaDevuelta.text = dateSelected.toString()
        val nameSelected = intent.getStringExtra("NAME SELECTED")
        mainBinding.idTextNombreDevuelto.text = nameSelected.toString()
    }

    private fun initEvent() {
        mainBinding.idBtnCall.setOnClickListener {
            val intentCall = Intent(this, LlamadaActivity::class.java)
            startActivity(intentCall)
        }

        mainBinding.idBtnAlarm.setOnClickListener {
            createAlarm("Alarma", 21, 21)
        }

        mainBinding.idBtnWebsite.setOnClickListener {
            createWebsite()
        }
        mainBinding.idBtnEmail.setOnClickListener {
            crearCorreo()
        }

        mainBinding.idBtnDados.setOnClickListener {
            val intentDados = Intent(this, DadosActivity::class.java)
            startActivity(intentDados)
        }

        mainBinding.idBtnChistes.setOnClickListener {
            val intentChistes = Intent(this, ChistesActivity::class.java)
            startActivity(intentChistes)
        }
    }

    private fun crearCorreo() {
        val intentCorreo = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, "josemagomez53@gmail.com")
            putExtra(Intent.EXTRA_SUBJECT, "")
        }
        startActivity(intentCorreo)
    }

    private fun createWebsite() {
        val url = "https://developer.android.com/kotlin?hl=es-419"
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(urlIntent)
    }

    private fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        startActivity(intent)
    }
}