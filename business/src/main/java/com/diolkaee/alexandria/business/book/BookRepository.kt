package com.diolkaee.alexandria.business.book

import com.diolkaee.alexandria.data.networking.ApiService
import com.diolkaee.alexandria.data.networking.BookData
import com.diolkaee.alexandria.data.networking.retrieveBooks
import com.diolkaee.alexandria.data.persistence.BookDao
import com.diolkaee.alexandria.data.persistence.BookEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.net.HttpURLConnection.HTTP_NOT_FOUND

class BookRepository(
    private val apiService: ApiService,
    private val bookDao: BookDao,
    private val mocked: Boolean,
) {
    val archive: Flow<List<Book>> = if (mocked) {
        flowOf(EXAMPLE_BOOKS)
    } else {
        bookDao
            .getAll()
            .map { archive -> archive.map { bookEntity -> bookEntity.toDomainObject() } }
    }

    suspend fun insert(book: Book) {
        if (!mocked) {
            bookDao.insert(book.toEntity())
        }
    }

    suspend fun insertAll(books: List<Book>) {
        if (!mocked) {
            bookDao.insertAll(books.map { it.toEntity() })
        }
    }

    suspend fun retrieve(isbn: Long) =
        if (!mocked) {
            bookDao.findByIsbn(isbn)?.toDomainObject()
        } else {
            EXAMPLE_BOOKS.find { it.isbn == isbn } ?: EXAMPLE_BOOKS.first()
        }

    suspend fun remove(book: Book) {
        if (!mocked) {
            bookDao.delete(book.toEntity())
        }
    }

    suspend fun fetch(isbn: Long): List<Book> = if (!mocked) {
        try {
            apiService.retrieveBooks(isbn).map { it.toDomainObject() }
        } catch (e: HttpException) {
            when (e.code()) {
                HTTP_NOT_FOUND -> emptyList()
                else -> throw e
            }
        }
    } else {
        EXAMPLE_BOOKS
    }
}

private fun BookData.toDomainObject() = Book(
    isbn = isbn!!.toLong(), // isbn will always be set by [BookListAdapter]
    title = title,
    author = authors.first().name.toAuthor(),
    pageCount = number_of_pages,
    publicationYear = publish_date,
    publisher = publishers.first().name,
    thumbnailUrl = cover?.medium,
)

private fun BookEntity.toDomainObject() = Book(
    isbn = isbn,
    title = title,
    author = Author(authorFirstName, authorSurname),
    publicationYear = publicationYear,
    publisher = publisher,
    pageCount = pageCount,
    thumbnailUrl = thumbnailUrl,
    flagged = flagged,
    rating = rating,
    notes = notes,
)

private fun Book.toEntity() = BookEntity(
    isbn = isbn,
    title = title,
    authorFirstName = author.firstName,
    authorSurname = author.surname,
    publicationYear = publicationYear,
    publisher = publisher,
    pageCount = pageCount,
    thumbnailUrl = thumbnailUrl,
    flagged = flagged,
    rating = rating,
    notes = notes,
)

private fun String.toAuthor(): Author {
    val surname = this.substringAfterLast(' ')
    val firstName = if (surname == this) null else this.substringBeforeLast(' ')
    return Author(firstName = firstName, surname = surname)
}
