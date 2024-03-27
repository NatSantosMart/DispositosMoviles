package com.spendTogether

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class CreateGroupActivity : AppCompatActivity() {

    val participantList = mutableListOf<String>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_group)

        initSpinner()
        initAddParticipantButton()
    }

    private fun initSpinner() {

        val options = arrayOf("Casa compartida", "Viaje", "Celebración", "Proyecto", "Evento", "Otro")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, options)

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autocompleteTextView)
        autoCompleteTextView.setAdapter(adapter)

        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        textInputLayout.hint = "Seleccione una opción"
    }

    private fun initAddParticipantButton() {
        val buttonAddParticipant: Button = findViewById(R.id.buttonAddParticipant)
        val nameAllPeopleGroup: TextView = findViewById(R.id.nameAllPeopleGroup)

        buttonAddParticipant.setOnClickListener {
            val nameInput: EditText = findViewById(R.id.name_participant_input)
            val participantName = nameInput.text.toString()
            participantList.add(participantName)
            val numberedParticipants = participantList.mapIndexed { index, name ->
                "${index + 1}. $name"
            }.joinToString(", ")

            val charSequence: CharSequence = numberedParticipants;
            nameAllPeopleGroup.text = charSequence
        }
    }
}
