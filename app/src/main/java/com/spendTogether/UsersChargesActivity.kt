package com.spendTogether

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.adapters.GroupAdapter
import com.spendTogether.adapters.usersMoneyAdapter
import com.spendTogether.models.ExpenseResponse.ExpenseResponse
import com.spendTogether.models.ExpenseResponse.ExpenseResponseItem
import com.spendTogether.service.RetrofitExpenseServiceFactory
import com.spendTogether.service.RetrofitServiceFactory
import kotlinx.coroutines.launch


class UsersChargesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: usersMoneyAdapter
    private val expensesInit = mutableListOf<ExpenseResponseItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_users_charges)

        val intent = this.intent
        val groupId =  intent.getStringExtra("groupId") ?: "default"

       // val groupId = intent.getIntExtra("groupId", -1);
        val text_group_name: TextView = findViewById(R.id.text_group_name);

        val apiExpenseService = RetrofitExpenseServiceFactory.getApiService()
        val apiGroupsService = RetrofitServiceFactory.getApiService()


        lifecycleScope.launch {
            try {
                val group = apiGroupsService.getGroupById(groupId)
                text_group_name.setText(group.name);
            } catch (e: Exception) {
                // Manejar el error
            }
        }

        val participants = listOf("David", "Maria")

        //Capturamos el RecyclerView
        val rvExpenses: RecyclerView = findViewById(R.id.rvExpenses)

        //Montamos el recycler de Users
        rvExpenses.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val expensesAdapter = usersMoneyAdapter(expensesInit)
        rvExpenses.adapter = expensesAdapter


        //Crear la petición
        lifecycleScope.launch {
            //Hacemos petición
            val allExpenses = apiExpenseService.getExpenses("expenses")

            // Filtrar los gastos por groupId
            val expensesByGroup = allExpenses.filter { it.idGroup == groupId }

            expensesInit.clear()
            expensesInit.addAll(expensesByGroup)
            //Repintar el RecyclerView
            expensesAdapter.notifyDataSetChanged()
        }


    }
}

