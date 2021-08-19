package com.example.quizdascapitais

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.TaskStackBuilder
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.quizdascapitais.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }


}