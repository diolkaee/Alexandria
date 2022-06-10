package com.diolkaee.alexandria.business.book

import com.diolkaee.alexandria.data.networking.ApiService
import com.diolkaee.alexandria.data.networking.BookData
import com.diolkaee.alexandria.data.networking.retrieveBooks
import com.diolkaee.alexandria.data.persistence.BookDao
import com.diolkaee.alexandria.data.persistence.BookEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.net.HttpURLConnection.HTTP_NOT_FOUND

class BookRepository(
    private val apiService: ApiService,
    private val bookDao: BookDao,
) {
    val archive: Flow<List<Book>> = bookDao
        .getAll()
        .map { archive -> archive.map { bookEntity -> bookEntity.toDomainObject() } }

    suspend fun insert(book: Book) {
        bookDao.insert(book.toEntity())
    }

    suspend fun insertAll(books: List<Book>) {
        bookDao.insertAll(books.map { it.toEntity() })
    }

    suspend fun retrieve(isbn: Long) = bookDao.get(isbn)?.toDomainObject()

    suspend fun remove(book: Book) = bookDao.delete(book.toEntity())

    suspend fun fetch(isbn: Long): List<Book> = try {
        apiService.retrieveBooks(isbn).map { it.toDomainObject() }
    } catch (e: HttpException) {
        when (e.code()) {
            HTTP_NOT_FOUND -> emptyList()
            else -> throw e
        }
    }
}

// TODO Improve entity mapping
private fun BookData.toDomainObject() = Book(
    isbn = isbn!!.toLong(),
    title = title,
    author = authors.first().name,
    pageCount = number_of_pages,
    publicationYear = publish_date,
    publisher = publishers.first().name,
    thumbnailUrl = cover?.medium
)

private fun BookEntity.toDomainObject() = Book(
    isbn = isbn,
    title = title,
    author = author,
    publicationYear = publicationYear,
    publisher = publisher,
    pageCount = pageCount,
    thumbnailUrl = thumbnailUrl,
    marked = marked,
    rating = rating,
    notes = notes,
)

private fun Book.toEntity() = BookEntity(
    isbn = isbn,
    title = title,
    author = author,
    publicationYear = publicationYear,
    publisher = publisher,
    pageCount = pageCount,
    thumbnailUrl = thumbnailUrl,
    marked = marked,
    rating = rating,
    notes = notes,
)
