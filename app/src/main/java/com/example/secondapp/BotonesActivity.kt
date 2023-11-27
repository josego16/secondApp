package com.example.secondapp

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
    }
}