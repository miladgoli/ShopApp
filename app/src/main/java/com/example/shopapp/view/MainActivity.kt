package com.example.shopapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.shopapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setup Navigation View And Bottom Navigation
        navigationSetups()

    }

    fun navigationSetups(){
        val navView = findViewById<BottomNavigationView>(R.id.bottomNavigationMain)
        val navController = Navigation.findNavController(this, R.id.mainFragment)
        NavigationUI.setupWithNavController(navView, navController)
    }
}