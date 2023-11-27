package com.example.secondapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.secondapp.databinding.ActivityLlamadaBinding

class LlamadaActivity : AppCompatActivity() {
    private lateinit var llamadaBinding: ActivityLlamadaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        llamadaBinding = ActivityLlamadaBinding.inflate(layoutInflater)
        setContentView(llamadaBinding.root)
        /*setContentView(R.layout.activity_llamada)*/
    }
}