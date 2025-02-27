package com.diolkaee.alexandria.ui.shelf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diolkaee.alexandria.business.BuildConfig
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import com.diolkaee.alexandria.business.book.EXAMPLE_BOOKS
import com.diolkaee.alexandria.common.flowFilter
import com.diolkaee.alexandria.common.next
import com.diolkaee.alexandria.common.sortBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class ShelfLayout {
    LIST,
    PREVIEW,
    GRID,
}

class ShelfViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val _bookFilter = MutableStateFlow<(Book) -> Boolean> { true }

    private val _books = bookRepository.archive.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val books: StateFlow<List<Book>> = _books
        .flowFilter(_bookFilter)
        .sortBy { it.author.surname }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _scrollPosition = MutableStateFlow(0)
    val scrollPosition: StateFlow<Int> = _scrollPosition

    private val _layout = MutableStateFlow(ShelfLayout.LIST)
    val layout: StateFlow<ShelfLayout> = _layout

    init {
        if (BuildConfig.DEBUG) {
            viewModelScope.launch {
                EXAMPLE_BOOKS.forEach { bookRepository.insert(it) }
            }
        }
    }

    fun setHighlightedBookIndex(newValue: Int) {
        _scrollPosition.value = newValue
    }

    fun advanceLayout() {
        _layout.update { it.next() }
    }

    fun setQuery(query: String?) {
        val searchFilter = if (query.isNullOrBlank()) {
            { true }
        } else {
            { book: Book? -> book?.contains(query) ?: true }
        }

        _bookFilter.value = searchFilter
    }
}

private fun Book.contains(searchQuery: String) =
    author.toString().contains(searchQuery, ignoreCase = true) || this.title.contains(searchQuery, ignoreCase = true)
