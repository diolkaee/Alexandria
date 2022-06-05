package com.diolkaee.alexandria.ui.shelf

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diolkaee.alexandria.business.BuildConfig
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import com.diolkaee.alexandria.business.book.EXAMPLE_BOOKS
import com.diolkaee.alexandria.common.filter
import com.diolkaee.alexandria.common.next
import com.diolkaee.alexandria.common.sortBy
import kotlinx.coroutines.launch

enum class Sorting {
    ALPHABETICAL_TITLE,
    ALPHABETICAL_AUTHOR,
}

enum class ShelfLayout {
    LIST,
    PREVIEW,
    GRID,
}

class ShelfViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val _bookFilter = MutableLiveData<(Book?) -> Boolean> { true }

    private val _books = MutableLiveData<List<Book>>(emptyList())
    val books: LiveData<List<Book>> = _books.filter(_bookFilter).sortBy { it.author }

    private val _scrollPosition = MutableLiveData(0)
    val scrollPosition: LiveData<Int> = _scrollPosition

    // TODO Add functionality
    private val _sorting = MutableLiveData(Sorting.ALPHABETICAL_TITLE)
    val sorting: LiveData<Sorting> = _sorting

    private val _layout = MutableLiveData(ShelfLayout.LIST)
    val layout: LiveData<ShelfLayout> = _layout

    init {
        observeArchive()
        if (BuildConfig.DEBUG) viewModelScope.launch {
            EXAMPLE_BOOKS.forEach { bookRepository.archiveBook(it) }
        }
    }

    fun setHighlightedBookIndex(newValue: Int) {
        _scrollPosition.value = newValue
    }

    fun toggleSorting() {
        _sorting.value = sorting.value?.next()
    }

    fun advanceLayout() {
        _layout.value = layout.value?.next()
    }

    fun setQuery(query: String?) {
        val searchFilter = if (query.isNullOrBlank()) {
            { true }
        } else { book: Book? -> book?.contains(query) ?: true }

        _bookFilter.value = searchFilter
    }

    fun navigateToDetails(book: Book) {
        // TODO Change to navigation
        viewModelScope.launch {
            bookRepository.archiveBook(book.copy(read = !book.read))
        }
    }

    private fun observeArchive() {
        viewModelScope.launch {
            bookRepository.archive.collect {
                _books.value = it
            }
        }
    }
}

// TODO Implement fuzzy search
private fun Book.contains(searchQuery: String) =
    this.author.contains(searchQuery, ignoreCase = true) ||
            this.title.contains(searchQuery, ignoreCase = true)
