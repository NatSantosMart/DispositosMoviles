package com.spendTogether.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.R
import com.spendTogether.UsersChargesActivity
import com.spendTogether.models.Group
import com.spendTogether.models.GroupResponse.GroupResponseItem
import com.spendTogether.views.GroupViewHolder

class GroupAdapter(private val groups: List<GroupResponseItem>, private val onclick: (GroupResponseItem)->Unit) : RecyclerView.Adapter<GroupViewHolder> (){


    //Inflar el elemento o view a manejar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_list_item, parent, false);
        return GroupViewHolder(view, onclick);
    }

    //Contar el numero total de items
    override fun getItemCount(): Int {
        return groups.size;
    }

    //Adaptar una de las vistas

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val group = groups[position]
        holder.bind(group)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, UsersChargesActivity::class.java)
            intent.putExtra("groupId", group.id)
            context.startActivity(intent)
        }
    }

}