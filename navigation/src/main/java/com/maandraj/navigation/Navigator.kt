package com.maandraj.navigation

import androidx.annotation.MainThread
import androidx.navigation.NavController

class Navigator(
    private val navController: NavController,
) {
    @MainThread
    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        is NavigationFlow.ProductsFlow -> navController.navigate(MainNavGraphDirections.actionGlobalCatalogFlow())
    }
}