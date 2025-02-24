package com.diolkaee.alexandria.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val isbn: Long,
    private val bookRepository: BookRepository,
) : ViewModel() {
    private val _book = MutableStateFlow<Book?>(null)
    val book: StateFlow<Book?> = _book

    init {
        viewModelScope.launch {
            _book.value = bookRepository.retrieve(isbn)
        }
    }

    fun setRating(rating: Float) {
        val updatedBook = _book.value?.copy(rating = rating)
        if (updatedBook != null) {
            updateBook(updatedBook)
        }
    }

    fun setNotes(notes: String?) {
        val updatedBook = _book.value?.copy(notes = notes)
        if (updatedBook != null) {
            updateBook(updatedBook)
        }
    }

    fun toggleMark() {
        val wasMarked = _book.value?.marked == true
        val updatedBook = _book.value?.copy(marked = !wasMarked)
        if (updatedBook != null) {
            updateBook(updatedBook)
        }
    }

    fun deleteBook() {
        val book = _book.value
        if (book != null) {
            viewModelScope.launch {
                bookRepository.remove(book)
            }
        }
    }

    private fun updateBook(newValue: Book) {
        _book.update { newValue }
        viewModelScope.launch {
            bookRepository.insert(newValue)
        }
    }
}
