package com.djevannn.capstoneproject.utils

import com.djevannn.capstoneproject.data.source.local.entity.BookEntity
import com.djevannn.capstoneproject.data.source.remote.response.BookResponse

object DataMapper {

    fun mapResponseToEntities(input: List<BookResponse>): List<BookEntity> =
        input.map {
            BookEntity(it.title, it.author, it.image, it.url)
        }

}