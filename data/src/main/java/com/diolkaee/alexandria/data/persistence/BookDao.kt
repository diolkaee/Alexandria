package com.diolkaee.alexandria.data.persistence

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: BookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BookEntity>)

    @Delete
    suspend fun delete(book: BookEntity)

    @Query("SELECT * FROM books WHERE isbn = :isbn")
    suspend fun get(isbn: Long): BookEntity

    @Query("SELECT * FROM books")
    fun getAll(): Flow<List<BookEntity>>
}
