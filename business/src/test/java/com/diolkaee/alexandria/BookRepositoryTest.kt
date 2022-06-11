package com.diolkaee.alexandria

import com.diolkaee.alexandria.business.book.Author
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import com.diolkaee.alexandria.data.networking.*
import com.diolkaee.alexandria.data.persistence.BookDao
import com.diolkaee.alexandria.data.persistence.BookEntity
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
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
    marked = false,
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
    marked = false,
)

internal class BookRepositoryTest {
    @Test
    fun `GIVEN any state WHEN the archive is called THEN it contains all persisted books`(): Unit = runBlocking {
        // GIVEN
        val given = Given()

        // WHEN
        val archive = given.repository.archive

        // THEN
        assert(archive.first() == listOf(TEST_BOOK))
    }

    @Test
    fun `GIVEN any state WHEN a book is inserted THEN it is persisted`(): Unit = runBlocking {
        // GIVEN
        val given = Given()

        // WHEN
        given.repository.insert(TEST_BOOK)

        // THEN
        coVerify { given.bookDao.insert(any()) }
    }

    @Test
    fun `GIVEN any state WHEN multiple books are inserted THEN they are persisted`(): Unit = runBlocking {
        // GIVEN
        val given = Given()

        // WHEN
        given.repository.insertAll(listOf(TEST_BOOK))

        // THEN
        coVerify { given.bookDao.insertAll(any()) }
    }

    @Test
    fun `GIVEN any state WHEN an isbn is retrieved THEN the book is retrieved from persistence`(): Unit = runBlocking {
        // GIVEN
        val given = Given()

        // WHEN
        val book = given.repository.retrieve(TEST_BOOK.isbn)

        // THEN
        assert(book == TEST_BOOK)
    }

    @Test
    fun `GIVEN any state WHEN a book is removed THEN it is deleted from persistence`(): Unit = runBlocking {
        // GIVEN
        val given = Given()

        // WHEN
        given.repository.remove(TEST_BOOK)

        // THEN
        coVerify { given.bookDao.delete(TEST_ENTITY) }
    }

    @Test
    fun `GIVEN any state WHEN an isbn is fetched THEN the book is fetched from API`(): Unit = runBlocking {
        // GIVEN
        val given = Given()

        // WHEN
        val books = given.repository.fetch(TEST_BOOK.isbn)

        // THEN
        assert(books.contains(TEST_BOOK))
    }

    private class Given {
        val apiService = mockk<ApiService> {
            coEvery { this@mockk.retrieveBooks(TEST_BOOK.isbn) } returns listOf(TEST_DATA)
        }
        val bookDao = mockk<BookDao> {
            coEvery { this@mockk.get(TEST_BOOK.isbn) } returns TEST_ENTITY
            coEvery { this@mockk.getAll() } returns flowOf(listOf(TEST_ENTITY))
            coJustRun { this@mockk.insert(TEST_ENTITY) }
            coJustRun { this@mockk.insertAll(listOf(TEST_ENTITY)) }
            coJustRun { this@mockk.delete(TEST_ENTITY) }
        }
        val repository = BookRepository(apiService, bookDao)
    }
}
