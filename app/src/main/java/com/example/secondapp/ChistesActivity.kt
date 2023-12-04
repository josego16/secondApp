package com.example.secondapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import com.example.secondapp.databinding.ActivityChistesBinding
import java.util.Locale
import java.util.Random

class ChistesActivity : AppCompatActivity() {
    private lateinit var chistesBinding: ActivityChistesBinding
    private lateinit var textToSpeech: TextToSpeech
    private var touchMaxTime = 500
    private var touchLastTime: Long = 0
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chistesBinding = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(chistesBinding.root)
        configureTextSpech()
        initEvent()
        recibirDatos()
    }

    private fun configureTextSpech() {
        textToSpeech = TextToSpeech(applicationContext) {
            if (it != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.getDefault()
            } else {
                error("Error en la configuracion")
            }
        }
    }

    private fun initEvent() {
        chistesBinding.idBtnEscuchar.setOnClickListener {
            val currentTime = System.currentTimeMillis()
            if (currentTime - touchLastTime < touchMaxTime) {
                val random = Random()
                val chisteRandom = Listachistes.chistes[random.nextInt(Listachistes.chistes.size)]
                textToSpeech.speak(chisteRandom, TextToSpeech.QUEUE_FLUSH, null, null)
            }
            touchLastTime = currentTime
        }
        chistesBinding.idBtnVolver.setOnClickListener {
            val intentVolver = Intent(this, MainActivity::class.java)
            startActivity(intentVolver)
        }
    }

    private fun recibirDatos() {
        val dateReceived = intent.getStringExtra("DATE_SELECTED")
        chistesBinding.idTextDateDevuelta.text = dateReceived.toString()
        val nameReceived = intent.getStringExtra("NAME_SELECTED")
        chistesBinding.idTextNameDevuelto.text = nameReceived.toString()
    }

    override fun onDestroy() {
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}