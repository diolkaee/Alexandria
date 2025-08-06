package com.diolkaee.alexandria

import com.diolkaee.alexandria.business.book.Author
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import com.diolkaee.alexandria.data.networking.ApiService
import com.diolkaee.alexandria.data.networking.AuthorData
import com.diolkaee.alexandria.data.networking.BookData
import com.diolkaee.alexandria.data.networking.PublisherData
import com.diolkaee.alexandria.data.networking.retrieveBooks
import com.diolkaee.alexandria.data.persistence.BookDao
import com.diolkaee.alexandria.data.persistence.BookEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

private val TEST_DATA = BookData(
    isbn = "9781111111111",
    publishers = listOf(PublisherData("Penguin Books")),
    title = "The picture of Dorian Gray",
    number_of_pages = 304,
    authors = listOf(AuthorData("Oscar Wilde")),
    publish_date = "2003",
    cover = null,
)

private val TEST_ENTITY = BookEntity(
    isbn = 9781111111111,
    authorFirstName = "Oscar",
    authorSurname = "Wilde",
    title = "The picture of Dorian Gray",
    publicationYear = "2003",
    publisher = "Penguin Books",
    pageCount = 304,
    thumbnailUrl = null,
    flagged = false,
    rating = null,
    notes = null,
)

private val TEST_BOOK = Book(
    isbn = 9781111111111,
    author = Author("Oscar", "Wilde"),
    title = "The picture of Dorian Gray",
    publicationYear = "2003",
    publisher = "Penguin Books",
    pageCount = 304,
    thumbnailUrl = null,
    flagged = false,
)

internal class BookRepositoryTest {
    private lateinit var remoteSource: ApiService
    private lateinit var localSource: BookDao
    private lateinit var repository: BookRepository

    @Before
    fun createRepository() {
        remoteSource = mockk<ApiService> {
            coEvery { this@mockk.retrieveBooks(TEST_BOOK.isbn) } returns listOf(TEST_DATA)
        }
        localSource = mockk<BookDao>(relaxed = true) {
            coEvery { this@mockk.findByIsbn(TEST_BOOK.isbn) } returns TEST_ENTITY
            coEvery { this@mockk.getAll() } returns flowOf(listOf(TEST_ENTITY))
        }
        repository = BookRepository(remoteSource, localSource)
    }

    @Test
    fun `WHEN the archive is called THEN it contains all persisted books`(): Unit = runBlocking {
        // WHEN
        val archive = repository.archive

        // THEN
        assert(archive.first() == listOf(TEST_BOOK))
    }

    @Test
    fun `WHEN a book is inserted THEN it is persisted`(): Unit = runBlocking {
        // WHEN
        repository.insert(TEST_BOOK)

        // THEN
        coVerify { localSource.insert(any()) }
    }

    @Test
    fun `WHEN multiple books are inserted THEN they are persisted`(): Unit = runBlocking {
        // WHEN
        repository.insertAll(listOf(TEST_BOOK))

        // THEN
        coVerify { localSource.insertAll(any()) }
    }

    @Test
    fun `WHEN an isbn is retrieved THEN the book is retrieved from persistence`(): Unit = runBlocking {
        // WHEN
        val book = repository.retrieve(TEST_BOOK.isbn)

        // THEN
        assert(book == TEST_BOOK)
    }

    @Test
    fun `WHEN a book is removed THEN it is deleted from persistence`(): Unit = runBlocking {
        // WHEN
        repository.remove(TEST_BOOK)

        // THEN
        coVerify { localSource.delete(TEST_ENTITY) }
    }

    @Test
    fun `WHEN an isbn is fetched THEN the book is fetched from API`(): Unit = runBlocking {
        // WHEN
        val books = repository.fetch(TEST_BOOK.isbn)

        // THEN
        assert(books.contains(TEST_BOOK))
    }
}
