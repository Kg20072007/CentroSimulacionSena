package com.example.centrosimulacionenfermeria

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class RoleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role)

        val instructor = findViewById<CardView>(R.id.cardInstructor)
        val admin = findViewById<CardView>(R.id.cardAdmin)
        val estudiante = findViewById<CardView>(R.id.cardEstudiante)

        instructor.setOnClickListener {
            startActivity(
                Intent(this, InstructorHomeActivity::class.java)
            )
        }

        admin.setOnClickListener {
            startActivity(
                Intent(this, AdminHomeActivity::class.java)
            )
        }

        estudiante.setOnClickListener {
            startActivity(
                Intent(this, EstudianteHomeActivity::class.java)
            )
        }
    }
}