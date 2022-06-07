package com.diolkaee.alexandria.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun getBookDao(): BookDao

    companion object {
        const val FILE_NAME = "book_database"
    }
}
