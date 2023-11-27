package com.example.secondapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.secondapp.databinding.ActivityChistesBinding

class ChistesActivity : AppCompatActivity() {
    private lateinit var chistesBinding: ActivityChistesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chistesBinding = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(chistesBinding.root)
        /*setContentView(R.layout.activity_chistes)*/
    }
}