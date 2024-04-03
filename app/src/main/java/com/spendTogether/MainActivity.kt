package com.spendTogether

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.spendTogether.adapters.GroupAdapter
import com.spendTogether.models.Group
import com.spendTogether.models.GroupResponse.GroupResponseItem
import com.spendTogether.service.RetrofitServiceFactory
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val groupsInit = mutableListOf<GroupResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {

            //Lanzamos el Splash
            val screenSplash = installSplashScreen()

            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)

        //Evento de navegación a pantalla CreateGroupActivity
            val plus_button: FloatingActionButton = findViewById(R.id.plus_button)

            plus_button.setOnClickListener {
                val intent = Intent(this, CreateGroupActivity::class.java)
                startActivity(intent)
            }

        //Capturamos el RecyclerView
            val rvGroups: RecyclerView = findViewById(R.id.rvGroups)

        //Montamos el Recycler de Groups
            val groupsAdapter = GroupAdapter(groupsInit);
            rvGroups.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rvGroups.adapter = groupsAdapter;

        //Crear la petición
            val apiGroupsService = RetrofitServiceFactory.getApiService();
            lifecycleScope.launch {
                //Hacemos petición
                val data = apiGroupsService.getGroups(("groups"));
                groupsInit.clear();
                groupsInit.addAll(data)
                //Repintar el RecyclerView
                groupsAdapter.notifyDataSetChanged()
            }
        }
    }
