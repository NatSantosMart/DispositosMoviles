package com.spendTogether.views

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.R
import com.spendTogether.models.ExpenseResponse.ExpenseResponseItem
import java.text.NumberFormat
import java.util.Locale

class UsersMoneyViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val expenseName: TextView = view.findViewById(R.id.expenseName)
    private val expenseMoney: TextView = view.findViewById(R.id.expenseMoney)
    private val expenseDate: TextView = view.findViewById(R.id.expenseDate)
    private val expenseUser: TextView = view.findViewById(R.id.expenseUser)

    fun render(expense: ExpenseResponseItem) {
        expenseUser.text = expense.paidBy
        expenseMoney.text = expense.quantity
        expenseDate.text = expense.date
        expenseName.text = expense.name
    }

    fun bind(expense: ExpenseResponseItem) {
        expenseUser.text = "Pagado por ${expense.paidBy}"
        expenseMoney.text = formatCurrency(expense.quantity.toDouble())
        expenseDate.text = expense.date
        expenseName.text = expense.name
    }

    private fun formatCurrency(amount: Double): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("es", "ES")) // Definir el formato de la moneda (euro)
        return formatter.format(amount) // Formatear la cantidad con el símbolo del euro
    }
}