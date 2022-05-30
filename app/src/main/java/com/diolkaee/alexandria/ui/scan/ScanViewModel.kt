package com.diolkaee.alexandria.ui.scan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import kotlinx.coroutines.launch

private const val SCAN_VM_TAG = "ScanViewModel"

class ScanViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val _books = MutableLiveData<Set<Book>>(emptySet())
    val books: LiveData<Set<Book>> = _books

    private val currentSearch = mutableListOf<String>()

    /**
     * Search for the incoming ISBNs. We need to keep track of currently searched ISBNs,
     * since the scanner updates faster than we get API results.
     */
    fun searchBooks(ISBNs: List<String>) {
        for (isbn in ISBNs) {
            if (!currentSearch.contains(isbn) && books.value?.containsIsbn(isbn) == false) {
                currentSearch.add(isbn)
                viewModelScope.launch {
                    val result = bookRepository.fetchBook(isbn)
                    if (result != null) {
                        _books.value = _books.value?.plus(result)
                        Log.d(SCAN_VM_TAG, "Added book $result")
                    }
                    currentSearch.remove(isbn)
                }
            }
        }
    }

    fun saveBook(book: Book) {
        viewModelScope.launch {
            bookRepository.submitBook(book)
        }
    }
}

private fun Set<Book>.containsIsbn(isbn: String) = map { it.id }.contains(isbn)
