package com.diolkaee.alexandria.common.list

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class CommonDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem::class.java == newItem::class.java

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}
