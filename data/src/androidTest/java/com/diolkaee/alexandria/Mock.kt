package com.diolkaee.alexandria

import com.diolkaee.alexandria.data.persistence.BookEntity

internal val mockBookDao = BookEntity(
    isbn = 9781111111111,
    authorFirstName = "Oscar",
    authorSurname = "Wilde",
    title = "The picture of Dorian Gray",
    publicationYear = "2003",
    publisher = "Penguin Books",
    pageCount = 304,
    thumbnailUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1631088473l/35031085._SY475_.jpg",
    flagged = false,
    rating = 3.5f,
    notes = "A marvelous book",
)

internal val mockNoThumbnailBookDao = BookEntity(
    isbn = 9781111111112,
    authorFirstName = "Mary",
    authorSurname = "Shelley",
    title = "Frankenstein",
    publisher = "Penguins Books",
    publicationYear = "2020",
    pageCount = 96,
    thumbnailUrl = null,
    flagged = true,
    rating = null,
    notes = null,
)

internal val mockBookDaoList = listOf(mockBookDao, mockNoThumbnailBookDao)