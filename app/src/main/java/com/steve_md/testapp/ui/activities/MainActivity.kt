package com.steve_md.testapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.steve_md.testapp.R
import dagger.hilt.android.AndroidEntryPoint



class MainActivity : AppCompatActivity(){

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hiding the Actionbar and toolbar
       //supportActionBar?.hide()


        val navHostFragment  = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navController = navHostFragment.findNavController()


        // Handle Navigation BackStack
        setupActionBarWithNavController(navController)

        onSupportNavigateUp()

    }

}