package com.example.centrosimulacionenfermeria

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class InstructorHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_home)

        val simulaciones = findViewById<CardView>(R.id.cardSimulaciones)
        val estudiantes = findViewById<CardView>(R.id.cardEstudiantes)
        val reportes = findViewById<CardView>(R.id.cardReportes)

        simulaciones.setOnClickListener {
            startActivity(Intent(this, SimulacionesActivity::class.java))
        }

        estudiantes.setOnClickListener {
            startActivity(Intent(this, EstudiantesActivity::class.java))
        }

        reportes.setOnClickListener {
            startActivity(Intent(this, ReportesActivity::class.java))
        }
    }
}