package com.diolkaee.alexandria

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diolkaee.alexandria.data.persistence.BookDao
import com.diolkaee.alexandria.data.persistence.BookDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

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
        dao.insert(mockBookDao)

        // WHEN
        val loaded = dao.findByIsbn(mockBookDao.isbn)

        // THEN
        assert(loaded == mockBookDao)
    }

    @Test
    fun insertMultipleBookAndGetAll(): Unit = runBlocking {
        // GIVEN
        val books = mockBookDaoList
        dao.insertAll(books)

        // WHEN
        val loaded = dao.getAll()

        // THEN
        assert(loaded.first() == books)
    }

    @Test
    fun insertBookWithConflictAndGetByIsbn(): Unit = runBlocking {
        // GIVEN
        dao.insert(mockBookDao)

        // WHEN
        val alteredCopy = mockBookDao.copy(title = "The Happy Prince")
        dao.insert(alteredCopy)

        // THEN
        val loaded = dao.findByIsbn(mockBookDao.isbn)
        assert(loaded == alteredCopy)
    }

    @Test
    fun deleteBookAndGetByIsbn(): Unit = runBlocking {
        // GIVEN
        dao.insert(mockBookDao)

        // WHEN
        dao.delete(mockBookDao)

        // THEN
        val loaded = dao.findByIsbn(mockBookDao.isbn)
        assert(loaded == null)
    }
}
