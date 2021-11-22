package com.example.finalplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // With androidx.fragment.app.FragmentContainerView being navHostFragment, the initialisation has
        // to be done in following manner by using findFragmentById
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment?

        if (navHostFragment != null) {
            navController = navHostFragment.navController
            NavigationUI.setupActionBarWithNavController(this, navController)
        }
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}
