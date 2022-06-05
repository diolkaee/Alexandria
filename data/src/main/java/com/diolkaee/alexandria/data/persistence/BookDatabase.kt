package com.diolkaee.alexandria.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

const val BOOK_DATABASE_NAME = "book_database"

@Database(entities = [BookEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun getBookDao(): BookDao
}
