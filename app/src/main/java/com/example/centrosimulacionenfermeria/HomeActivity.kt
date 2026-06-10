package com.example.centrosimulacionenfermeria

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val btnServicios = findViewById<Button>(R.id.btnServicios)
        val btnContacto = findViewById<Button>(R.id.btnContacto)
        val btnSimulaciones = findViewById<Button>(R.id.btnSimulaciones)

        btnServicios.setOnClickListener {

            val intent = Intent(this, ServiciosActivity::class.java)
            startActivity(intent)

        }

        btnContacto.setOnClickListener {

            val intent = Intent(this, ContactoActivity::class.java)
            startActivity(intent)

        }

        btnSimulaciones.setOnClickListener {

            val intent = Intent(this, SimulacionesActivity::class.java)
            startActivity(intent)

        }
    }
}