package com.diolkaee.alexandria.ui.shelf

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import kotlinx.coroutines.launch

enum class SORTING {
    ALPHABETICAL_TITLE,
    ALPHABETICAL_AUTHOR
}

class ShelfViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val _books = MutableLiveData<List<Book>>(emptyList())
    val books: LiveData<List<Book>> = _books

    private val _highlightedBookIndex = MutableLiveData(0)
    val highlightedBookIndex: LiveData<Int> = _highlightedBookIndex

    private val _sorting = MutableLiveData(SORTING.ALPHABETICAL_TITLE)
    val sorting: LiveData<SORTING> = _sorting

    init {
        observeArchive()
    }

    fun setHighlightedBookIndex(newValue: Int) {
        _highlightedBookIndex.value = newValue
    }

    fun toggleSorting() {
        _books.value = books.value?.sortedBy {
            when (sorting.value) {
                SORTING.ALPHABETICAL_TITLE -> it.title
                SORTING.ALPHABETICAL_AUTHOR -> it.author
                else -> {
                    it.title
                } // TODO Replace with actual logic
            }
        }
        // TODO Replace with actual logic
        _sorting.value =
            if (_sorting.value == SORTING.ALPHABETICAL_TITLE) SORTING.ALPHABETICAL_AUTHOR
            else SORTING.ALPHABETICAL_TITLE
    }

    private fun observeArchive() {
        viewModelScope.launch {
            bookRepository.archive.collect {
                _books.value = it
            }
        }
    }
}
