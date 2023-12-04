package com.example.secondapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.AlarmClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.secondapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initHandler()
        initEvent()
        peticionDatos()
    }

    private fun initHandler() {
        handler = Handler(Looper.getMainLooper())
        mainBinding.progressBar.visibility = View.VISIBLE
        mainBinding.idLayoutApp.visibility = View.GONE
        handler.postDelayed({
            mainBinding.progressBar.visibility = View.GONE
            mainBinding.idLayoutApp.visibility = View.VISIBLE
        }, 3000)
    }

    private fun peticionDatos() {
        val dateSelected = intent.getStringExtra("DATE_SELECTED")
        mainBinding.idTextFechaDevuelta.text = dateSelected.toString()
        val nameSelected = intent.getStringExtra("NAME_SELECTED")
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
            val dateReceived = mainBinding.idTextFechaDevuelta.text.toString()
            val nameReceived = mainBinding.idTextNombreDevuelto.text.toString()

            val intentChistes = Intent(this, ChistesActivity::class.java)
            intent.putExtra("DATE_SELECTED", dateReceived)
            intent.putExtra("NAME_SELECTED", nameReceived)
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