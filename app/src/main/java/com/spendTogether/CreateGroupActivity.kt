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
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import com.spendTogether.models.GroupResponse.GroupResponseItem
import com.spendTogether.service.ApiGroups
import com.spendTogether.service.RetrofitServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID

class CreateGroupActivity : AppCompatActivity() {

    private val apiGroupsService = RetrofitServiceFactory.getApiService();

    val participantList = mutableListOf<String>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_group)

        initSpinner()
        initAddParticipantButton()
        initSaveGroup()
    }

    private fun initSpinner() {

        val options = arrayOf("Casa compartida", "Viaje", "Celebración", "Proyecto", "Evento", "Otro")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, options)

        val categoria_selected = findViewById<AutoCompleteTextView>(R.id.categoria_selected)
        categoria_selected.setAdapter(adapter)

        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        textInputLayout.hint = "Seleccione una opción"
    }

    private fun initAddParticipantButton() {
        val buttonAddParticipant: Button = findViewById(R.id.buttonAddParticipant)
        val participants_input: TextView = findViewById(R.id.participants_input)

        buttonAddParticipant.setOnClickListener {
            val nameInput: EditText = findViewById(R.id.name_participant_input)
            val participantName = nameInput.text.toString()
            participantList.add(participantName)
            val numberedParticipants = participantList.mapIndexed { index, name ->
                "${index + 1}. $name"
            }.joinToString(", ")

            val charSequence: CharSequence = numberedParticipants;
            participants_input.text = charSequence
        }
    }

    private fun initSaveGroup() {
        val buttonSave: Button = findViewById(R.id.buttonSave)
        val name_input: EditText = findViewById(R.id.name_input)
        val description_input: EditText = findViewById(R.id.description_input)
        val categoria_selected: AutoCompleteTextView = findViewById(R.id.categoria_selected)
        val participants_input: TextView = findViewById(R.id.participants_input)

        buttonSave.setOnClickListener {
            val name: String = name_input.text.toString().trim()
            val description: String = description_input.text.toString().trim()
            val category: String = categoria_selected.text.toString().trim()
            val participants: List<String> = participantList.map { it.substringAfter(". ").trim() }

            val newGroup = GroupResponseItem(UUID.randomUUID().toString(), name, description, category, participants)
                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        apiGroupsService.addGroup("groups", newGroup)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
        }
    }
}
