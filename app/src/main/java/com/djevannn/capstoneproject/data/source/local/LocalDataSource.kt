package com.djevannn.capstoneproject.data.source.local

import com.djevannn.capstoneproject.data.source.local.entity.BookEntity
import com.djevannn.capstoneproject.data.source.local.room.BookDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val bookDao: BookDao) {

    fun getAllBooks(): Flow<List<BookEntity>> =
        bookDao.getAllBooks()

    suspend fun insertBooks(bookList: List<BookEntity>) =
        bookDao.insertBooks(bookList)


}