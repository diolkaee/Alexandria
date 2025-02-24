package com.example.composeapp.ui

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.ui.shelf.ShelfNavigation
import com.example.composeapp.ui.shelf.shelfScreen

@Composable
fun MainScreen() {
    val navHost = rememberNavController()

    NavHost(
        modifier = Modifier.safeDrawingPadding(),
        navController = navHost,
        startDestination = ShelfNavigation.ROUTE,
    ) {
        shelfScreen()
    }
}
