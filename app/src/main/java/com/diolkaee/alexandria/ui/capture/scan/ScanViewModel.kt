package com.diolkaee.alexandria.ui.capture.scan

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val LOG_TAG = "ScanViewModel"

class ScanViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val _searchResults = MutableStateFlow<Set<Book>>(emptySet())
    val searchResults: StateFlow<Set<Book>> = _searchResults

    private val currentSearch = mutableListOf<Long>()

    /**
     * Search for the incoming ISBNs. We need to keep track of currently searched ISBNs,
     * since the scanner updates faster than we get API results.
     */
    fun searchBooks(isbns: List<Long>) {
        for (isbn in isbns) {
            if (!currentSearch.contains(isbn) && !_searchResults.value.containsIsbn(isbn)) {
                currentSearch.add(isbn)
                viewModelScope.launch {
                    val results = bookRepository.fetch(isbn)
                    if (results.isNotEmpty()) {
                        _searchResults.update { it.plus(results) }
                        Log.d(LOG_TAG, "Added book $results")
                    }
                    currentSearch.remove(isbn)
                }
            }
        }
    }
}

private fun Set<Book>.containsIsbn(isbn: Long) = map { it.isbn }.contains(isbn)
