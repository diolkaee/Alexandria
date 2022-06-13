package com.diolkaee.alexandria

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diolkaee.alexandria.data.persistence.BookDao
import com.diolkaee.alexandria.data.persistence.BookDatabase
import com.diolkaee.alexandria.data.persistence.BookEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

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
    rating = 3.5f,
    notes = "A marvelous book",
)

private val TEST_ENTITY_2 = BookEntity(
    isbn = 9781111111112,
    authorFirstName = "Mary",
    authorSurname = "Shelley",
    title = "Frankenstein",
    publisher = "Penguins Books",
    publicationYear = "2020",
    pageCount = 96,
    thumbnailUrl = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1631088473l/35031085._SY475_.jpg",
    marked = true,
    rating = null,
    notes = null,
)

@RunWith(AndroidJUnit4::class)
class BookDatabaseTest {
    private lateinit var db: BookDatabase
    private lateinit var dao: BookDao

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room
            .inMemoryDatabaseBuilder(context, BookDatabase::class.java)
            .build()
        dao = db.getBookDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun insertBookAndGetByIsbn(): Unit = runBlocking {
        // GIVEN
        dao.insert(TEST_ENTITY)

        // WHEN
        val loaded = dao.get(TEST_ENTITY.isbn)

        // THEN
        assert(loaded == TEST_ENTITY)
    }

    @Test
    fun insertMultipleBookAndGetAll(): Unit = runBlocking {
        // GIVEN
        val books = listOf(TEST_ENTITY, TEST_ENTITY_2)
        dao.insertAll(books)

        // WHEN
        val loaded = dao.getAll()

        // THEN
        assert(loaded.first() == books)
    }

    @Test
    fun insertBookWithConflictAndGetByIsbn(): Unit = runBlocking {
        // GIVEN
        dao.insert(TEST_ENTITY)

        // WHEN
        val alteredCopy = TEST_ENTITY.copy(title = "The Happy Prince")
        dao.insert(alteredCopy)

        // THEN
        val loaded = dao.get(TEST_ENTITY.isbn)
        assert(loaded == alteredCopy)
    }

    @Test
    fun deleteBookAndGetByIsbn(): Unit = runBlocking {
        // GIVEN
        dao.insert(TEST_ENTITY)

        // WHEN
        dao.delete(TEST_ENTITY)

        // THEN
        val loaded = dao.get(TEST_ENTITY.isbn)
        assert(loaded == null)
    }
}
