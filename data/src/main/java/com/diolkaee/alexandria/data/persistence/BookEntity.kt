package com.diolkaee.alexandria.data.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val isbn: Long,
    val author: String,
    val title: String,
    val publicationYear: Int,
    val publisher: String,
    val pageCount: Int?,
    val thumbnailUrl: String?
)
