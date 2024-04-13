package com.spendTogether

import android.os.Bundle
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.spendTogether.models.ExpenseResponse.ExpenseResponseItem
import com.spendTogether.service.ApiExpense
import com.spendTogether.service.RetrofitExpenseServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID

class CreateExpenseActivity : AppCompatActivity() {

    private val apiExpenseService = RetrofitExpenseServiceFactory.getApiService();
    private lateinit var participantsAutoCompleteTextView: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_expense)
        participantsAutoCompleteTextView = findViewById(R.id.participantsAutoCompleteTextView)
        initSpinner()
        initSaveExpense()
    }

    private fun initSpinner() {

        val participants = arrayOf("Participant 1", "Participant 2", "Participant 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, participants)
        participantsAutoCompleteTextView.setAdapter(adapter)
    }

    private fun initSaveExpense() {
        val buttonSave: Button = findViewById(R.id.buttonSave)
        val name_input: EditText = findViewById(R.id.name_input)
        val quantity_input: EditText = findViewById(R.id.quantity_input)

        buttonSave.setOnClickListener {
            val name: String = name_input.text.toString().trim()
            val quantity: String = quantity_input.text.toString().trim()
            val paidBy: String = "Ana";

            // Verificar si algún campo está vacío
            if (name.isEmpty() || quantity.isEmpty() || paidBy.isEmpty()){
                val toast = Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT)
                val toastLayout = layoutInflater.inflate(R.layout.error_message, null)
                toast.setGravity(Gravity.TOP, 0, 0)
                val textView = toastLayout.findViewById<TextView>(R.id.text_toast)
                textView.text = "Por favor, complete todos los campos"
                toast.view = toastLayout
                toast.show()
                return@setOnClickListener
            }

            val newExpense = ExpenseResponseItem(UUID.randomUUID().toString(),name, quantity, "", "", "1")
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    apiExpenseService.addExpense("expenses", newExpense)
                    //Navegación a la vista de los gastos del viaje

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }
    }
}
