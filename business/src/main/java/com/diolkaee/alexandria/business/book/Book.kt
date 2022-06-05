@file:Suppress("MaxLineLength")

package com.diolkaee.alexandria.business.book

val EXAMPLE_BOOKS = listOf(
    Book(
        id = "9780141439570",
        isbn = "978-0141439570",
        author = "Oscar Wilde",
        title = "The picture of Dorian Gray",
        publisher = "Penguins Books",
        publicationYear = "2003",
        pageCount = 304,
    ),
    Book(
        id = "9788664341737",
        isbn = "979-8664341737",
        author = "Mary Shelley",
        title = "Frankenstein",
        publisher = "Penguins Books",
        publicationYear = "2020",
        pageCount = 96,
        thumbnailUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1631088473l/35031085._SY475_.jpg"
    ),
    Book(
        id = "9781713326472",
        isbn = "978-1713326472",
        author = "Robert Louis Stevenson",
        title = "The strange case of Dr. Jekyll and Mr. Hyde",
        publisher = "Penguins Books",
        publicationYear = "2019",
        pageCount = 110,
    ),
    Book(
        id = "9780393347098",
        isbn = "978-0393347098",
        author = "Franz Kafka",
        title = "The metamorphosis",
        publisher = "Penguins Books",
        publicationYear = "1972",
        pageCount = 201,
    ),
    Book(
        id = "9789083127033",
        isbn = "978-9083127033",
        author = "F. Scott Fitzgerald",
        title = "The great Gatsby",
        publisher = "Penguins Books",
        publicationYear = "2021",
        pageCount = 114,
        thumbnailUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1490528560l/4671._SY475_.jpg"
    )
)

data class Book(
    val id: String, // Internal variation of isbn without hyphens. Used to compare scanned and archived books
    val isbn: String,
    val author: String,
    val title: String,
    val publicationYear: String,
    val publisher: String,
    val pageCount: Int?,
    val thumbnailUrl: String? = null,
    val read: Boolean = false
)
