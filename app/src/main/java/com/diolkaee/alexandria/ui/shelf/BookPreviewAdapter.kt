package com.diolkaee.alexandria.ui.shelf

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.common.list.BookClickListener
import com.diolkaee.alexandria.common.list.CommonDiffCallback
import com.diolkaee.alexandria.databinding.ItemBookCoverBinding
import com.diolkaee.alexandria.databinding.ItemBookCoverShelfBinding

class BookPreviewAdapter :
    ListAdapter<Book, BookPreviewAdapter.ViewHolder>(CommonDiffCallback<Book>()) {

    class ViewHolder(val binding: ItemBookCoverBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemId(position: Int): Long = currentList[position].isbn

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBookCoverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.book = currentList[position]
    }
}

class BookGridAdapter(private val clickListener: BookClickListener) :
    ListAdapter<Book, BookGridAdapter.ViewHolder>(CommonDiffCallback<Book>()) {

    class ViewHolder(val binding: ItemBookCoverShelfBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemId(position: Int): Long = currentList[position].isbn

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBookCoverShelfBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = currentList[position]
        holder.binding.book = book
        holder.binding.setOnBookClick { clickListener(book) }
    }
}
