package com.diolkaee.alexandria.data.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val isbn: Long,
    val authorFirstName: String?,
    val authorSurname: String,
    val title: String,
    val publicationYear: String,
    val publisher: String,
    val pageCount: Int?,
    val thumbnailUrl: String?,
    val flagged: Boolean,
    val rating: Float?,
    val notes: String?,
)
