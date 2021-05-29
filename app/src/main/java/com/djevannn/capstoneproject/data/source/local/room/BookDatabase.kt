package com.djevannn.capstoneproject.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.djevannn.capstoneproject.data.source.local.entity.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BookDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
}