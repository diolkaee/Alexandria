package com.example.composeapp.ui.shelf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class ShelfState(
    val books: List<Book>,
    val query: String = "",
)

class ShelfViewModel(bookRepository: BookRepository) : ViewModel() {
    private val queryState = MutableStateFlow("")

    val uiState =
        combine(
            bookRepository.archive.combine(queryState) { books, query ->
                books.filter { it.contains(query) }
            },
            queryState,
            ::ShelfState,
        ).stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            ShelfState(emptyList()),
        )

    fun setQuery(query: String) {
        queryState.update { query }
    }
}

private fun Book.contains(searchQuery: String) =
    author.toString().contains(searchQuery, ignoreCase = true) || this.title.contains(
        searchQuery,
        ignoreCase = true,
    )
