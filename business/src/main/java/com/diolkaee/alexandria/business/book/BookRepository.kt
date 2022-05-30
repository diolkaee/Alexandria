package com.diolkaee.alexandria.business.book

import com.diolkaee.alexandria.data.networking.ApiService
import com.diolkaee.alexandria.data.networking.BookData
import com.diolkaee.alexandria.data.networking.IdentifierData
import com.diolkaee.alexandria.data.networking.retrieveBook
import com.diolkaee.alexandria.data.persistence.BookDao
import com.diolkaee.alexandria.data.persistence.BookEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import retrofit2.HttpException
import java.net.HttpURLConnection.HTTP_NOT_FOUND

class BookRepository(
    private val apiService: ApiService,
    private val bookDao: BookDao,
    scope: CoroutineScope
) {
    val archive: StateFlow<List<Book>> = bookDao
        .getAll()
        .map { archive -> archive.map { bookEntity ->  bookEntity.toDomainObject() } }
        .stateIn(scope, SharingStarted.Lazily, emptyList())

    suspend fun submitBook(book: Book) {
        bookDao.insert(book.toEntity())
    }

    suspend fun fetchBook(isbn13: String): Book? = try {
        apiService.retrieveBook(isbn13)?.toDomainObject()
    } catch (e: HttpException) {
        when (e.code()) {
            HTTP_NOT_FOUND -> null
            else -> throw e
        }
    }
}

// TODO Improve entity mapping
private fun BookData.toDomainObject() = Book(
    id = identifiers.toIsbn().replace("-", ""),
    isbn = identifiers.toIsbn(),
    title = title,
    author = authors.first().name,
    pageCount = number_of_pages,
    publicationYear = publish_date.toInt(),
    publisher = publishers.first().name,
    thumbnailUrl = cover?.medium
)

private fun IdentifierData.toIsbn() = isbn_13?.firstOrNull() ?: "978${isbn_10?.first()}"

private fun BookEntity.toDomainObject() = Book(
    id = isbn.toString(),
    isbn = isbn.formatIsbn(),
    title = title,
    author = author,
    publicationYear = publicationYear,
    publisher = publisher,
    pageCount = pageCount,
    thumbnailUrl = thumbnailUrl
)

// ISBN Format: 978-X-XXXXX-XXX-X
private fun Long.formatIsbn(): String {
    return toString().apply { "${slice(0..2)}-${take(3)}-${slice(4..8)}-${slice(9..11)}-${take(12)}" }
}

private fun Book.toEntity() = BookEntity(
    isbn = id.toLong(),
    title = title,
    author = author,
    publicationYear = publicationYear,
    publisher = publisher,
    pageCount = pageCount,
    thumbnailUrl = thumbnailUrl
)
