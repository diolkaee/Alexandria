package com.diolkaee.alexandria.ui.scan

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

data class ScanState(val searchResults: Set<Book> = emptySet())

class ScanViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val _state = MutableStateFlow(ScanState())
    val state: StateFlow<ScanState> = _state

    private val currentSearch = mutableListOf<String>()

    /**
     * Search for the incoming ISBNs. We need to keep track of currently searched ISBNs,
     * since the scanner updates faster than we get API results.
     */
    fun searchBooks(ISBNs: List<String>) {
        for (isbn in ISBNs) {
            if (!currentSearch.contains(isbn) && !_state.value.searchResults.containsIsbn(isbn)) {
                currentSearch.add(isbn)
                viewModelScope.launch {
                    val result = bookRepository.fetchBook(isbn)
                    if (result != null) {
                        _state.update { it.copy(searchResults = it.searchResults.plus(result)) }
                        Log.d(LOG_TAG, "Added book $result")
                    }
                    currentSearch.remove(isbn)
                }
            }
        }
    }

    fun saveBook(book: Book) {
        viewModelScope.launch {
            bookRepository.archiveBook(book)
        }
    }
}

private fun Set<Book>.containsIsbn(isbn: String) = map { it.id }.contains(isbn)
