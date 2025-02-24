package com.example.composeapp.ui.scan

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ScanScreen(
            uiState = uiState,
            onSearchBooks = viewModel::searchBooks,
        )
    }
}

fun NavController.showScan() {
    navigate(ScanNavigation.ROUTE)
}