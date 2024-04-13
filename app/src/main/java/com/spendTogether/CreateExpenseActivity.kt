package com.spendTogether

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.spendTogether.models.ExpenseResponse.ExpenseResponseItem
import com.spendTogether.service.ApiExpense
import com.spendTogether.service.RetrofitExpenseServiceFactory
import com.spendTogether.service.RetrofitServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class CreateExpenseActivity : AppCompatActivity() {

    private val apiExpenseService = RetrofitExpenseServiceFactory.getApiService();
    val apiGroupsService = RetrofitServiceFactory.getApiService()
    private lateinit var participantsAutoCompleteTextView: AutoCompleteTextView
    private lateinit var textInputEditTextDate: TextInputEditText
    private var selectedDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_expense)
        participantsAutoCompleteTextView = findViewById(R.id.participantsAutoCompleteTextView)
        textInputEditTextDate = findViewById(R.id.text_input_edit_text_date)
        val groupId = intent.getStringExtra("groupId")

        lifecycleScope.launch {
            try {
                val group = apiGroupsService.getGroupById(groupId.toString())
                val participants = group.participants

                // Inicializar el spinner después de obtener la lista de participantes
                initSpinner(participants)
            } catch (e: Exception) {
                // Manejar el error
            }
        }
        initSaveExpense(groupId.toString())
    }

    fun showDatePicker(view: View) {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        datePicker.addOnPositiveButtonClickListener { selectedDateInMillis ->
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.timeInMillis = selectedDateInMillis
            val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
            textInputEditTextDate.setText(formattedDate)
            selectedDate = formattedDate
        }
        datePicker.show(supportFragmentManager, "DatePicker")
    }

    private fun initSpinner(participants : List<String>) {

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, participants)
        participantsAutoCompleteTextView.setAdapter(adapter)

    }

    private fun initSaveExpense(groupId: String) {
        val buttonSave: Button = findViewById(R.id.buttonSave)
        val name_input: EditText = findViewById(R.id.name_input)
        val quantity_input: EditText = findViewById(R.id.quantity_input)
        var paidBy: String? = null

        participantsAutoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val selectedParticipant = parent.getItemAtPosition(position) as String
            paidBy = selectedParticipant
        }

        buttonSave.setOnClickListener {
            val name: String = name_input.text.toString().trim()
            val quantity: String = quantity_input.text.toString().trim()

            // Verificar si algún campo está vacío
            if (name.isEmpty() || quantity.isEmpty() || paidBy.isNullOrEmpty()){
                val toast = Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT)
                val toastLayout = layoutInflater.inflate(R.layout.error_message, null)
                toast.setGravity(Gravity.TOP, 0, 0)
                val textView = toastLayout.findViewById<TextView>(R.id.text_toast)
                textView.text = "Por favor, complete todos los campos"
                toast.view = toastLayout
                toast.show()
                return@setOnClickListener
            }

            val newExpense = ExpenseResponseItem(UUID.randomUUID().toString(),name, quantity, paidBy!!, selectedDate!!, groupId)
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    apiExpenseService.addExpense("expenses", newExpense)
                    //Navegación a la vista de los gastos del viaje

                    val intent = Intent(this@CreateExpenseActivity, UsersChargesActivity::class.java)
                    startActivity(intent)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }
    }
}
