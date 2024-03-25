package com.spendTogether

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CreateGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_group)


        val opciones = arrayOf("Casa compartida", "Viaje", "Celebración", "Proyecto", "Evento", "Otro")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.adapter = adapter
    }
}