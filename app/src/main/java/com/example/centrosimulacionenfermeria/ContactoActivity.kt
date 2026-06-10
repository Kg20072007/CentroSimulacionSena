package com.example.centrosimulacionenfermeria

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContactoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_contacto)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etMensaje = findViewById<EditText>(R.id.etMensaje)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener {

            val nombre = etNombre.text.toString()
            val mensaje = etMensaje.text.toString()

            if(nombre.isNotEmpty() && mensaje.isNotEmpty()) {

                Toast.makeText(
                    this,
                    "Mensaje enviado correctamente",
                    Toast.LENGTH_LONG
                ).show()

                etNombre.setText("")
                etMensaje.setText("")

            } else {

                Toast.makeText(
                    this,
                    "Complete todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}