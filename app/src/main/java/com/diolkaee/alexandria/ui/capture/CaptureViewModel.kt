package com.diolkaee.alexandria.ui.capture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SearchResult(val book: Book, val flagged: Boolean = false)

class CaptureViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val _searchResults = MutableStateFlow<List<SearchResult>>(emptyList())
    val searchResults: StateFlow<List<SearchResult>> = _searchResults

    fun fetchBooks(isbn: Long) {
        viewModelScope.launch {
            val results = bookRepository.fetch(isbn).map { SearchResult(it, flagged = false) }
            _searchResults.update { it.plus(results) }
        }
    }

    fun toggleFlag(isbn: Long) {
        _searchResults.update {
            it.map { result ->
                if (result.book.isbn == isbn) {
                    result.copy(flagged = !result.flagged)
                } else {
                    result
                }
            }
        }
    }

    fun archiveFlaggedBooks() = viewModelScope.launch {
        val flaggedBooks = searchResults.value
            .filter { it.flagged }
            .map { it.book }
        bookRepository.insertAll(flaggedBooks)
    }
}
