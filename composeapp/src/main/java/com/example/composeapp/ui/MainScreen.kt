package com.example.composeapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.ui.scan.scanScreen
import com.example.composeapp.ui.scan.showScan
import com.example.composeapp.ui.shelf.ShelfNavigation
import com.example.composeapp.ui.shelf.shelfScreen

@Composable
fun MainScreen() {
    val navHost = rememberNavController()

    NavHost(
        navController = navHost,
        startDestination = ShelfNavigation.ROUTE,
    ) {
        shelfScreen(onShowScan = navHost::showScan)
        scanScreen()
    }
}
