package com.diolkaee.alexandria.common.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diolkaee.alexandria.business.book.Book

fun interface BookClickListener {
    operator fun invoke(book: Book)
}

@BindingAdapter("books", "onBookClick", requireAll = false)
fun RecyclerView.setBooks(books: List<Book>, onBookClick: BookClickListener?) {
    // Cannot use default parameters in DataBinding, so shadowing it is
    val onBookClick = onBookClick ?: BookClickListener { }

    val adapter = this.adapter as? BookListAdapter ?: BookListAdapter(onBookClick).also {
        this.adapter = it
    }

    adapter.submitList(books)
}
