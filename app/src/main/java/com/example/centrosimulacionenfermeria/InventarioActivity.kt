package com.example.centrosimulacionenfermeria

import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class InventarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_inventario)

        val layoutEnero = findViewById<LinearLayout>(R.id.layoutEnero)
        val layoutFebrero = findViewById<LinearLayout>(R.id.layoutFebrero)
        val layoutMarzo = findViewById<LinearLayout>(R.id.layoutMarzo)
        val layoutAbril = findViewById<LinearLayout>(R.id.layoutAbril)
        val layoutMayo = findViewById<LinearLayout>(R.id.layoutMayo)

        val btnEnero = findViewById<Button>(R.id.btnEnero)
        val btnFebrero = findViewById<Button>(R.id.btnFebrero)
        val btnMarzo = findViewById<Button>(R.id.btnMarzo)
        val btnAbril = findViewById<Button>(R.id.btnAbril)
        val btnMayo = findViewById<Button>(R.id.btnMayo)

        val btnPDF = findViewById<Button>(R.id.btnPDF)

        btnEnero.setOnClickListener {

            ocultarTodo()

            layoutEnero.visibility = View.VISIBLE
        }

        btnFebrero.setOnClickListener {

            ocultarTodo()

            layoutFebrero.visibility = View.VISIBLE
        }

        btnMarzo.setOnClickListener {

            ocultarTodo()

            layoutMarzo.visibility = View.VISIBLE
        }

        btnAbril.setOnClickListener {

            ocultarTodo()

            layoutAbril.visibility = View.VISIBLE
        }

        btnMayo.setOnClickListener {

            ocultarTodo()

            layoutMayo.visibility = View.VISIBLE
        }

        btnPDF.setOnClickListener {

            generarPDF()
        }
    }

    private fun ocultarTodo() {

        findViewById<LinearLayout>(R.id.layoutEnero).visibility = View.GONE
        findViewById<LinearLayout>(R.id.layoutFebrero).visibility = View.GONE
        findViewById<LinearLayout>(R.id.layoutMarzo).visibility = View.GONE
        findViewById<LinearLayout>(R.id.layoutAbril).visibility = View.GONE
        findViewById<LinearLayout>(R.id.layoutMayo).visibility = View.GONE
    }

    private fun generarPDF() {

        val pdfDocument = PdfDocument()

        val paginaInfo =
            PdfDocument.PageInfo.Builder(400, 700, 1).create()

        val pagina = pdfDocument.startPage(paginaInfo)

        val canvas = pagina.canvas

        val paint = Paint()

        paint.textSize = 15f

        canvas.drawText(
            "Inventario Centro Simulación SENA",
            40f,
            50f,
            paint
        )

        canvas.drawText(
            "MAYO",
            40f,
            100f,
            paint
        )

        canvas.drawText(
            "- Jeringas: 420",
            60f,
            140f,
            paint
        )

        canvas.drawText(
            "- Tapabocas: 650",
            60f,
            170f,
            paint
        )

        canvas.drawText(
            "- Gorros: 300",
            60f,
            200f,
            paint
        )

        canvas.drawText(
            "- Gasas: 500",
            60f,
            230f,
            paint
        )

        pdfDocument.finishPage(pagina)

        val archivo = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            ),
            "InventarioSENA.pdf"
        )

        pdfDocument.writeTo(FileOutputStream(archivo))

        pdfDocument.close()

        Toast.makeText(
            this,
            "PDF descargado en Descargas",
            Toast.LENGTH_LONG
        ).show()
    }
}