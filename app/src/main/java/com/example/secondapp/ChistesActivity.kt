package com.example.secondapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
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
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chistesBinding = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(chistesBinding.root)
        configureTextSpech()
        initEvent()
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

    override fun onDestroy() {
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}