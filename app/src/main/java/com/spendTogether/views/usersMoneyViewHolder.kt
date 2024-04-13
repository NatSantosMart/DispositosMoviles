package com.spendTogether.views

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.R
import com.spendTogether.models.ExpenseResponse.ExpenseResponseItem
import com.spendTogether.models.GroupResponse.GroupResponseItem

class usersMoneyViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val userName: TextView = view.findViewById(R.id.userName);
    private val userMoney: TextView = view.findViewById(R.id.userMoney);

    fun render(expense: ExpenseResponseItem) {
        userName.text = expense.paidBy;
        userMoney.text = expense.quantity
    }
}