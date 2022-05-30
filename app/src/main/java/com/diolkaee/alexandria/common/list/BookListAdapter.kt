package com.diolkaee.alexandria.common.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.databinding.ItemBookBinding

class BookListAdapter(private val onBookClickListener: BookClickListener) :
    ListAdapter<Book, BookListAdapter.ViewHolder>(BookDiffCallback) {

    class ViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = currentList[position]
        holder.binding.book = data
        holder.binding.setOnClick { onBookClickListener(data) }
    }
}

object BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
        oldItem::class.java == newItem::class.java

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem == newItem
}
