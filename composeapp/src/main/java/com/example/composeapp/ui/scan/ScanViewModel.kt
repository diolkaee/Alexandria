package com.example.composeapp.ui.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ScanState(val searchResults: Set<Book> = emptySet())

class ScanViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val searchResultState = MutableStateFlow<Set<Book>>(emptySet())
    private val currentSearch = MutableStateFlow<List<Long>>(emptyList())

    val uiState = searchResultState
        .map(::ScanState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, ScanState())

    fun searchBooks(isbns: List<Long>) {
        for (isbn in isbns) {
            if (!currentSearch.value.contains(isbn) && !searchResultState.value.containsIsbn(isbn)) {
                currentSearch.update { it.plus(isbn) }
                viewModelScope.launch {
                    val results = bookRepository.fetch(isbn)
                    if (results.isNotEmpty()) {
                        searchResultState.value = searchResultState.value.plus(results)
                    }
                    currentSearch.update { it.minus(isbn) }
                }
            }
        }
    }
}

private fun Set<Book>.containsIsbn(isbn: Long) = any { it.isbn == isbn }