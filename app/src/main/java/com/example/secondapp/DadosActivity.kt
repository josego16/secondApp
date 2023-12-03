package com.example.secondapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.secondapp.databinding.ActivityDadosBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class DadosActivity : AppCompatActivity() {
    private lateinit var bindingDados: ActivityDadosBinding
    private var suma: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDados = ActivityDadosBinding.inflate(layoutInflater)
        initEvent()
    }

    private fun initEvent() {
        bindingDados.idTextResultado.visibility = View.INVISIBLE
        bindingDados.idBtnCubo.setOnClickListener {
            bindingDados.idTextResultado.visibility = View.VISIBLE
            iniciarJuego()
        }
    }

    private fun iniciarJuego() {
        planificarTiradas()
    }

    private fun planificarTiradas() {
        val planificador = Executors.newSingleThreadScheduledExecutor()
        val milisegundos = 1000

        for (i in 1..5) {
            planificador.schedule(
                {
                    lanzarDados()
                }, milisegundos * i.toLong(), TimeUnit.MILLISECONDS
            )
        }
        planificador.schedule(
            {
                resultadoFinal()
            }, milisegundos * 7.toLong(), TimeUnit.MILLISECONDS
        )
        planificador.shutdown()
    }

    private fun lanzarDados() {
        val numDados = Array(3) { Random.nextInt(1, 6) }
        val imgDados: Array<ImageView> = arrayOf(
            bindingDados.idImgCara1,
            bindingDados.idImgCara2,
            bindingDados.idImgCara3
        )
        suma = numDados.sum()
        for (i in 0..3) {
            seleccionadorCaras(imgDados[i], numDados[i])
        }
    }

    private fun seleccionadorCaras(imageView: ImageView, num: Int) {
        when (num) {
            1 -> imageView.setImageResource(R.drawable.dice_1)
            2 -> imageView.setImageResource(R.drawable.dice_2)
            3 -> imageView.setImageResource(R.drawable.dice_3)
            4 -> imageView.setImageResource(R.drawable.dice_4)
            5 -> imageView.setImageResource(R.drawable.dice_5)
            6 -> imageView.setImageResource(R.drawable.dice_6)
        }
    }

    private fun resultadoFinal() {
        bindingDados.idTextResultado.text = suma.toString()
        println(suma)
    }
}