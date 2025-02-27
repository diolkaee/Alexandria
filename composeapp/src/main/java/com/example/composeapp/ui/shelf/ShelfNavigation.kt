package com.example.composeapp.ui.shelf

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

object ShelfNavigation {
    const val ROUTE = "shelf"
}

fun NavGraphBuilder.shelfScreen(onShowScan: () -> Unit) {
    composable(ShelfNavigation.ROUTE) {
        val viewModel = koinViewModel<ShelfViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        ShelfScreen(
            uiState = uiState,
            onSearchQueryChange = viewModel::setQuery,
            onShowScan = onShowScan,
        )
    }
}
