package com.alex.carexpenses3

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.alex.carexpenses3.database.AppDatabase
import com.alex.carexpenses3.database.AppRepository
import com.alex.carexpenses3.databinding.ActivityMainBinding
import com.alex.carexpenses3.utils.APP_ACTIVITY
import com.alex.carexpenses3.utils.Preferences
import com.alex.carexpenses3.utils.REPOSITORY
import com.alex.carexpenses3.utils.TAG

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MainActivity.onCreate")

        APP_ACTIVITY = this
        val dao = AppDatabase.getInstance(this).getAppRoomDao()
        REPOSITORY = AppRepository(dao)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_list, R.id.navigation_info
            )
        )

        //Preferences.getPreferences(this)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}