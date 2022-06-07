package com.diolkaee.alexandria.ui.capture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SearchResult(val book: Book, val marked: Boolean = false)

class CaptureViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val _searchResults = MutableStateFlow<List<SearchResult>>(emptyList())
    val searchResults: StateFlow<List<SearchResult>> = _searchResults

    fun fetchBooks(isbn: Long) {
        viewModelScope.launch {
            val results = bookRepository.fetchBooks(isbn).map { SearchResult(it, marked = false) }
            _searchResults.update { it.plus(results) }
        }
    }

    // TODO Rename parameters
    fun toggleMarked(searchResult: SearchResult) {
        _searchResults.update {
            it.map { result ->
                if (result.book == searchResult.book) {
                    result.copy(marked = !result.marked)
                } else result
            }
        }
    }

    fun archiveMarkedBooks() = viewModelScope.launch {
        searchResults.value
            .filter { it.marked }
            .forEach { bookRepository.archiveBook(it.book) }
    }
}
