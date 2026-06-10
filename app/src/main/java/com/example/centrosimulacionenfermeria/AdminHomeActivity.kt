package com.example.centrosimulacionenfermeria

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class AdminHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        val inventario = findViewById<CardView>(R.id.cardInventario)
        val cronograma = findViewById<CardView>(R.id.cardCronograma)
        val simulaciones = findViewById<CardView>(R.id.cardSimulaciones)

        inventario.setOnClickListener {
            startActivity(Intent(this, InventarioActivity::class.java))
        }

        cronograma.setOnClickListener {
            startActivity(Intent(this, CronogramaActivity::class.java))
        }

        simulaciones.setOnClickListener {
            startActivity(Intent(this, SimulacionesActivity::class.java))
        }
    }
}