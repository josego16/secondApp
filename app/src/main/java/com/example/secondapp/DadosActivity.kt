package com.example.secondapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.secondapp.databinding.ActivityDadosBinding

class DadosActivity : AppCompatActivity() {
    private lateinit var dadosBinding: ActivityDadosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dadosBinding = ActivityDadosBinding.inflate(layoutInflater)
        /*setContentView(R.layout.activity_dados)*/
    }
}