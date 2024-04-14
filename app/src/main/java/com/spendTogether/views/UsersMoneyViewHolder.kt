package com.spendTogether.views

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.R
import com.spendTogether.models.ExpenseResponse.ExpenseResponseItem

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
        expenseUser.text = expense.paidBy
        expenseMoney.text = expense.quantity
        expenseDate.text = expense.date
        expenseName.text = expense.name
    }
}