package com.spendTogether.views

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.R
import com.spendTogether.models.Group
import com.spendTogether.models.GroupResponse.GroupResponseItem
import java.text.SimpleDateFormat
import java.util.Locale

class GroupViewHolder (view: View, val onClick: (GroupResponseItem)->Unit) : RecyclerView.ViewHolder(view){

    private val txtNameGroup: TextView = view.findViewById(R.id.txtNameGroup);
    private val txtDescriptionGroup: TextView = view.findViewById(R.id.txtDescriptionGroup);
    private var currentItem: GroupResponseItem? = null

    init {
        itemView.setOnClickListener {
            currentItem?.let {
                onClick(it)
            }
        }
    }

    //Modificar el view que recibamos
    fun render(group: GroupResponseItem) {
        currentItem = group
        txtNameGroup.text = group.name
        txtDescriptionGroup.text = group.description
    }
}