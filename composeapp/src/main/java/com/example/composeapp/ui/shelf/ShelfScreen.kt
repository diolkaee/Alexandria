package com.example.composeapp.ui.shelf

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diolkaee.alexandria.business.book.EXAMPLE_BOOKS
import com.example.composeapp.ui.common.AlexandriaScreenPreview
import com.example.composeapp.ui.shelf.ShelfScreenLayout.Grid
import com.example.composeapp.ui.shelf.ShelfScreenLayout.List
import com.example.composeapp.ui.shelf.ShelfScreenLayout.Shelf
import com.example.composeapp.ui.shelf.list.ShelfList
import com.example.composeapp.ui.shelf.pager.BookPager
import com.example.composeapp.ui.theme.AlexandriaTheme

private enum class ShelfScreenLayout {
    Shelf,
    List,
    Grid,
    ;

    fun next() = entries[(ordinal + 1) % entries.size]
}

@Composable
fun ShelfScreen(uiState: ShelfState, onSearchQueryChange: (String) -> Unit) {
    var shelfLayout by remember { mutableStateOf(Shelf) }
    var pagerPosition by remember { mutableIntStateOf(0) }

    Column(Modifier.animateContentSize()) {
        if (shelfLayout != List) {
            BookPager(
                modifier = Modifier.fillMaxWidth(),
                books = uiState.books,
                pagerPosition = pagerPosition,
                onPagerPositionChange = { pagerPosition = it },
            )
        }
        ShelfActionBar(
            modifier = Modifier.fillMaxWidth(),
            query = uiState.query,
            onQueryChange = onSearchQueryChange,
            onActionButtonClick = { shelfLayout = shelfLayout.next() },
        )
        if (shelfLayout != Grid) {
            ShelfList(
                modifier = Modifier.padding(horizontal = 16.dp),
                books = uiState.books,
                onBookClick = {},
                onScrollToIndex = { pagerPosition = it },
            )
        }
    }
}

@AlexandriaScreenPreview
@Composable
private fun Preview() {
    AlexandriaTheme {
        ShelfScreen(
            uiState = ShelfState(books = EXAMPLE_BOOKS),
            onSearchQueryChange = {},
        )
    }
}
