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
        /*setContentView(R.layout.activity_main)*/
        initEvent()
    }

    private fun initEvent() {
        mainBinding.idBtnCall.setOnClickListener {
            val intentcall = Intent(this, LlamadaActivity::class.java)
            startActivity(intentcall)
        }
        mainBinding.idBtnAlarm.setOnClickListener {
            createAlarm("Alarma", 21, 21)
        }
        mainBinding.idBtnEmail.setOnClickListener {
            crearWebsite()
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

    private fun crearWebsite() {
        val url = "https://developer.android.com/kotlin?hl=es-419"
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(urlIntent)
    }

    private fun crearCorreo() {
        val correo = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, "josemagomez53@gmail.com")
            putExtra(Intent.EXTRA_SUBJECT, "")
        }
        if (correo.resolveActivity(packageManager) != null) {
            startActivity(correo)
        }
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