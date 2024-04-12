package com.spendTogether.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.R
import com.spendTogether.views.usersMoneyViewHolder

class usersMoneyAdapter(private val participants: List<String>) : RecyclerView.Adapter<usersMoneyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): usersMoneyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_users_charges, parent, false);
        return usersMoneyViewHolder(view);
    }

    override fun getItemCount(): Int {
        return participants.size
    }

    override fun onBindViewHolder(holder: usersMoneyViewHolder, position: Int) {
        holder.render(participants[position])
    }

}