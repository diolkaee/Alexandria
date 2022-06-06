package com.diolkaee.alexandria.common.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.databinding.ItemAuthorBinding
import com.diolkaee.alexandria.databinding.ItemBookBinding

private const val ITEM_VIEW_TYPE_AUTHOR = 0
private const val ITEM_VIEW_TYPE_BOOK = 1

sealed class BookListItem {
    data class BookItem(val book: Book) : BookListItem()
    data class AuthorItem(val name: String) : BookListItem()
}

/**
 * RecyclerView adapter to display BookItems grouped by their author.
 * To add items to the adapter, use [updateList] instead of [submitList]!
 */
class BookListAdapter(private val clickListener: BookClickListener) :
    ListAdapter<BookListItem, RecyclerView.ViewHolder>(CommonDiffCallback<BookListItem>()) {

    class AuthorViewHolder(private val binding: ItemAuthorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String) {
            binding.name = name
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AuthorViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAuthorBinding.inflate(layoutInflater, parent, false)
                return AuthorViewHolder(binding)
            }
        }
    }

    class BookViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book, clickListener: BookClickListener) {
            binding.book = item
            binding.setOnClick { clickListener(item) }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): BookViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBookBinding.inflate(layoutInflater, parent, false)
                return BookViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_VIEW_TYPE_AUTHOR -> AuthorViewHolder.from(parent)
            ITEM_VIEW_TYPE_BOOK -> BookViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AuthorViewHolder -> {
                val authorItem = getItem(position) as BookListItem.AuthorItem
                holder.bind(authorItem.name)
            }
            is BookViewHolder -> {
                val bookItem = getItem(position) as BookListItem.BookItem
                holder.bind(bookItem.book, clickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is BookListItem.BookItem -> ITEM_VIEW_TYPE_BOOK
        is BookListItem.AuthorItem -> ITEM_VIEW_TYPE_AUTHOR
    }

    fun updateList(list: List<Book>?) {
        super.submitList(list?.toBookListItems())
    }
}

private fun List<Book>.toBookListItems() = mutableListOf<BookListItem>().also { items ->
    this.groupBy { book -> book.author }
        .forEach { (author, oeuvre) ->
            val authorItem = BookListItem.AuthorItem(author)
            val bookItems = oeuvre.map { BookListItem.BookItem(it) }
            items.addAll(listOf(authorItem) + bookItems)
        }
}
