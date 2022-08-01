package com.maandraj.warehouse.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.maandraj.navigation.NavigationFlow
import com.maandraj.navigation.Navigator
import com.maandraj.navigation.ToFlowNavigatable
import com.maandraj.warehouse.R


class MainActivity : AppCompatActivity(), ToFlowNavigatable {
    private var navigator: Navigator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?

        if (navHostFragment != null) {
            val navController = navHostFragment.navController
            navigator = Navigator(navController)
        }


    }

    override fun navigateToFlow(flow: NavigationFlow) {
        navigator?.navigateToFlow(flow)
    }
}