package com.djevannn.capstoneproject.data

import com.djevannn.capstoneproject.data.source.local.LocalDataSource
import com.djevannn.capstoneproject.data.source.local.entity.BookEntity
import com.djevannn.capstoneproject.data.source.remote.RemoteDataSource
import com.djevannn.capstoneproject.utils.DummyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BookRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : BookDataSource {

    override fun getAllBooks(): Flow<Resource<List<BookEntity>>> =
        flow {
//            emit(Resource.Success(DummyData.generateDummyBooks()))
            val list = ArrayList<BookEntity>() as List<BookEntity>
            emit(Resource.Success(list))
        }.flowOn(
            Dispatchers.Default
        )

}