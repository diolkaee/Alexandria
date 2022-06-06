package com.diolkaee.alexandria.ui.capture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diolkaee.alexandria.common.list.CommonDiffCallback
import com.diolkaee.alexandria.databinding.ItemBookResultBinding

fun interface ResultClickListener {
    operator fun invoke(result: SearchResult)
}

class SearchResultAdapter(private val clickListener: ResultClickListener) :
    ListAdapter<SearchResult, SearchResultAdapter.ViewHolder>(CommonDiffCallback<SearchResult>()) {
    class ViewHolder(val binding: ItemBookResultBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemId(position: Int): Long = currentList[position].book.isbn

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBookResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = currentList[position]
        holder.binding.book = result.book
        holder.binding.isAdded = result.marked
        holder.binding.setOnClick { clickListener(result) }
    }
}
