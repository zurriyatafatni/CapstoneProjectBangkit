package com.djevannn.capstoneproject.data

import com.djevannn.capstoneproject.data.source.local.LocalDataSource
import com.djevannn.capstoneproject.data.source.local.entity.BookEntity
import com.djevannn.capstoneproject.data.source.remote.RemoteDataSource
import com.djevannn.capstoneproject.data.source.remote.network.ApiResponse
import com.djevannn.capstoneproject.data.source.remote.response.BookResponse
import com.djevannn.capstoneproject.utils.DataMapper
import kotlinx.coroutines.flow.Flow

class BookRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : BookDataSource {

    override fun getAllBooks(): Flow<Resource<List<BookEntity>>> {
        return object :
            NetworkBoundResource<List<BookEntity>, List<BookResponse>>() {
            override fun loadFromDB(): Flow<List<BookEntity>> =
                localDataSource.getAllBooks()

            override fun shouldFetch(data: List<BookEntity>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<BookResponse>>> =
                remoteDataSource.getBookList()

            override suspend fun saveCallResult(data: List<BookResponse>) {
                val entities = DataMapper.mapResponseToEntities(data)
                localDataSource.insertBooks(entities)
            }

        }.asFlow()
    }


}