package com.spendTogether.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.R
import com.spendTogether.models.ExpenseResponse.ExpenseResponseItem
import com.spendTogether.views.UsersMoneyViewHolder

class UsersMoneyAdapter(private val expenses: List<ExpenseResponseItem>) : RecyclerView.Adapter<UsersMoneyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersMoneyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expense_list_item, parent, false)
        return UsersMoneyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return expenses.size
    }

    override fun onBindViewHolder(holder: UsersMoneyViewHolder, position: Int) {
        val expense = expenses[position]
        holder.bind(expense)
    }
}