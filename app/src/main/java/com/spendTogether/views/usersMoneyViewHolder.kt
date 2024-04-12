package com.spendTogether.views

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.R

class usersMoneyViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val txtNameUser: TextView = view.findViewById(R.id.userName);
    private val txtPrice: TextView = view.findViewById(R.id.userMoney);

    private val rcView: RecyclerView = view.findViewById(R.id.participantsRV)
    fun render(participant: String) {
        txtNameUser.text = participant
        txtPrice.text = "15"

    }
}