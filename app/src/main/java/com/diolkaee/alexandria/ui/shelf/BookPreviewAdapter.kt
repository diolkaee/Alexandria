package com.diolkaee.alexandria.ui.shelf

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.common.list.BookDiffCallback
import com.diolkaee.alexandria.databinding.ItemBookPreviewBinding

class BookPreviewAdapter : ListAdapter<Book, BookPreviewAdapter.ViewHolder>(BookDiffCallback) {

    class ViewHolder(val binding: ItemBookPreviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.book = currentList[position]
    }
}
