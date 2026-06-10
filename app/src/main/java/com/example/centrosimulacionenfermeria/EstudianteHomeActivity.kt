package com.example.centrosimulacionenfermeria

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class EstudianteHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiante_home)

        val simulaciones = findViewById<CardView>(R.id.cardSimulaciones)
        val cronograma = findViewById<CardView>(R.id.cardCronograma)
        val servicios = findViewById<CardView>(R.id.cardServicios)

        simulaciones.setOnClickListener {
            startActivity(Intent(this, SimulacionesActivity::class.java))
        }

        cronograma.setOnClickListener {
            startActivity(Intent(this, CronogramaActivity::class.java))
        }

        servicios.setOnClickListener {
            startActivity(Intent(this, ServiciosActivity::class.java))
        }
    }
}