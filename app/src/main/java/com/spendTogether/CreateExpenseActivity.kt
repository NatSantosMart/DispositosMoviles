package com.spendTogether

import android.os.Bundle
import android.view.Gravity
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

    val participantList = mutableListOf<String>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_expense)

       // initSpinner()
        initSaveExpense()
    }

    private fun initSaveExpense() {
        val buttonSave: Button = findViewById(R.id.buttonSave)
        val name_input: EditText = findViewById(R.id.name_input)
        val quantity_input: EditText = findViewById(R.id.quantity_input)

        buttonSave.setOnClickListener {
            val name: String = name_input.text.toString().trim()
            val quantity: String = quantity_input.text.toString().trim()
            val participant: String = "Ana";

            // Verificar si algún campo está vacío
            if (name.isEmpty() || quantity.isEmpty() || participant.isEmpty()){
                val toast = Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT)
                val toastLayout = layoutInflater.inflate(R.layout.error_message, null)
                toast.setGravity(Gravity.TOP, 0, 0)
                val textView = toastLayout.findViewById<TextView>(R.id.text_toast)
                textView.text = "Por favor, complete todos los campos"
                toast.view = toastLayout
                toast.show()
                return@setOnClickListener
            }

            val newExpense = ExpenseResponseItem(UUID.randomUUID().toString(), 1,name, quantity,"", "")
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    apiExpenseService.addExpense("expense", newExpense)
                    //Navegación a la vista de los gastos del viaje

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }
    }
}
