package com.diolkaee.alexandria

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diolkaee.alexandria.data.persistence.BookDao
import com.diolkaee.alexandria.data.persistence.BookDatabase
import com.diolkaee.alexandria.data.persistence.BookEntity
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

@RunWith(AndroidJUnit4::class)
class BookDatabaseTest {
    private lateinit var db: BookDatabase
    private lateinit var dao: BookDao

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java).build()
        dao = db.getBookDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertBook(): Unit = runBlocking {
        // Given
        assert(dao.get(TEST_ENTITY.isbn) == null)

        // WHEN
        dao.insert(TEST_ENTITY)

        // THEN
        assert(dao.get(TEST_ENTITY.isbn) == TEST_ENTITY)
    }

    @Test
    fun updateBook(): Unit = runBlocking {
        // Given
        dao.insert(TEST_ENTITY)
        assert(dao.get(TEST_ENTITY.isbn) == TEST_ENTITY)

        // WHEN
        val alteredCopy = TEST_ENTITY.copy(title = "The Happy Prince")
        dao.insert(alteredCopy)

        // THEN
        assert(dao.get(TEST_ENTITY.isbn) == alteredCopy)
        }

    @Test
    fun deleteBook(): Unit = runBlocking {
        // Given
        dao.insert(TEST_ENTITY)
        assert(dao.get(TEST_ENTITY.isbn) == TEST_ENTITY)

        // WHEN
        dao.delete(TEST_ENTITY)

        // THEN
        assert(dao.get(TEST_ENTITY.isbn) == null)
    }
}
