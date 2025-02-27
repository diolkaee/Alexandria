package com.example.composeapp.ui.scan

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

object ScanNavigation {
    const val ROUTE = "scan"
}

fun NavGraphBuilder.scanScreen() {
    composable(ScanNavigation.ROUTE) {
        val viewModel = koinViewModel<ScanViewModel>()

        ScanScreen(viewModel = viewModel)
    }
}

fun NavController.showScan() {
    navigate(ScanNavigation.ROUTE)
}