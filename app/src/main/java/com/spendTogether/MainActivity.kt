package com.spendTogether

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spendTogether.adapters.GroupAdapter
import com.spendTogether.models.Group
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val groups = listOf(
        Group(
            id = 1,
            name = "Viaje familiar",
            description = "A gathering of extended family members for a weekend getaway",
            category = "Family",
            date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse("23/02/2023"),
            participants = arrayListOf("John", "Alice", "Michael", "Emily")
        ),
        Group(
            id = 2,
            name = "Cumple Laura",
            description = "A group of avid readers meeting monthly to discuss literature",
            category = "Hobby",
            date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse("01/01/2024"),
            participants = arrayListOf("Sarah", "David", "Emma", "James", "Sophia")
        ),
        Group(
            id = 3,
            name = "Requena quedada",
            description = "A group committed to regular exercise and fitness activities",
            category = "Health",
            date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse("11/02/2024"),
            participants = arrayListOf("Chris", "Megan", "Ryan", "Olivia", "Daniel")
        ),
        Group(
            id = 4,
            name = "Cumple Laura",
            description = "A group of avid readers meeting monthly to discuss literature",
            category = "Hobby",
            date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse("01/01/2024"),
            participants = arrayListOf("Sarah", "David", "Emma", "James", "Sophia")
        ),
        Group(
            id = 5,
            name = "Cumple Laura",
            description = "A group of avid readers meeting monthly to discuss literature",
            category = "Hobby",
            date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse("01/01/2024"),
            participants = arrayListOf("Sarah", "David", "Emma", "James", "Sophia")
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)

            val rvGroups: RecyclerView = findViewById(R.id.rvGroups)
            val groupsAdapter = GroupAdapter(groups);
            rvGroups.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rvGroups.adapter = groupsAdapter;
        }
    }
