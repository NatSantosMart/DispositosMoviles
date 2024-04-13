package com.spendTogether.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.R
import com.spendTogether.models.ExpenseResponse.ExpenseResponseItem
import com.spendTogether.models.GroupResponse.GroupResponseItem
import com.spendTogether.views.GroupViewHolder
import com.spendTogether.views.usersMoneyViewHolder

class usersMoneyAdapter(private val expenses: List<ExpenseResponseItem>) : RecyclerView.Adapter<usersMoneyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): usersMoneyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_users_charges, parent, false)
        return usersMoneyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return expenses.size
    }

    override fun onBindViewHolder(holder: usersMoneyViewHolder, position: Int) {
        val expense = expenses[position]
        holder.render(expense)
    }
}