package com.djevannn.capstoneproject.data

import com.djevannn.capstoneproject.data.source.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow

interface BookDataSource {

     fun getAllBooks(): Flow<Resource<List<BookEntity>>>

}