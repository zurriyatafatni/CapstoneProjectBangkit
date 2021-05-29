package com.djevannn.capstoneproject.data.source.local.room

import androidx.room.*
import com.djevannn.capstoneproject.data.source.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM book_entities")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(tourism: List<BookEntity>)

}