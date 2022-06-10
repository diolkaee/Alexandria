@file:Suppress("MaxLineLength")

package com.diolkaee.alexandria.business.book

import androidx.annotation.FloatRange

val EXAMPLE_BOOKS = listOf(
    Book(
        isbn = 9780141439570,
        author = Author("Oscar", "Wilde"),
        title = "The picture of Dorian Gray",
        publisher = "Penguins Books",
        publicationYear = "2003",
        pageCount = 304,
    ),
    Book(
        isbn = 9798664341737,
        author = Author("Mary", "Shelley"),
        title = "Frankenstein",
        publisher = "Penguins Books",
        publicationYear = "2020",
        pageCount = 96,
        thumbnailUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1631088473l/35031085._SY475_.jpg"
    ),
    Book(
        isbn = 9781713326472,
        author = Author("Robert Louis", "Stevenson"),
        title = "The strange case of Dr. Jekyll and Mr. Hyde",
        publisher = "Penguins Books",
        publicationYear = "2019",
        pageCount = 110,
    ),
    Book(
        isbn = 9780393347098,
        author = Author("Franz", "Kafka"),
        title = "The metamorphosis",
        publisher = "Penguins Books",
        publicationYear = "1972",
        pageCount = 201,
    ),
    Book(
        isbn = 9789083127033,
        author = Author("F. Scott", "Fitzgerald"),
        title = "The great Gatsby",
        publisher = "Penguins Books",
        publicationYear = "2021",
        pageCount = 114,
        thumbnailUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1490528560l/4671._SY475_.jpg"
    )
)

data class Book(
    val isbn: Long,
    val author: Author,
    val title: String,
    val publicationYear: String,
    val publisher: String,
    val marked: Boolean = false,
    val pageCount: Int?,
    val thumbnailUrl: String? = null,
    @FloatRange(from = 1.0, to = 5.0)
    val rating: Float? = null,
    val notes: String? = null
)

data class Author(val firstName: String?, val surname: String) {
    override fun toString(): String {
        return if (firstName != null) "$firstName $surname"
        else surname
    }
}
