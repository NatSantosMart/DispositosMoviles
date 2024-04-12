package com.spendTogether

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.adapters.GroupAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.spendTogether.adapters.usersMoneyAdapter

import com.spendTogether.service.RetrofitServiceFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers


class UsersCharges : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: usersMoneyAdapter
    private val usersInit = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_users_charges)
        val participants2 = listOf("David", "Maria")
        //Capturamos el reccyclerView
        val rvUsers: RecyclerView = findViewById(R.id.participantsRV)

        //Montamos el recycler de Users
        rvUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val usersAdapter = usersMoneyAdapter(participants2)
        rvUsers.adapter = usersAdapter


        //Creamos la peticion
        val apiGroupService = RetrofitServiceFactory.getApiService();
        lifecycleScope.launch {
            //val data = apiGroupService.getGroups("groups")
            //mockeo el grupo que toca
            val participants = listOf("David", "Maria")
            usersInit.clear()
            usersInit.addAll(participants)

            usersAdapter.notifyDataSetChanged()
        }


        val btnExpenses = findViewById<Button>(R.id.btn_expenses)
        val btnRefunds = findViewById<Button>(R.id.btn_refunds)

        btnExpenses.isEnabled = false
        btnRefunds.setOnClickListener {
            //Pendiende de que se agregue la vista de los reembolsos
            //val intent = Intent(this, RefundsActivity::class.java)
            //startActivity(intent)
        }
    }
}