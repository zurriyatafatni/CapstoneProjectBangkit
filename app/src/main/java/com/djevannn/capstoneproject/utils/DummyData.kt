package com.djevannn.capstoneproject.utils

import com.djevannn.capstoneproject.data.source.local.entity.BookEntity

object DummyData {

    fun generateDummyBooks(): List<BookEntity> {
        val books = ArrayList<BookEntity>()
        books.add(
            BookEntity(
                "Nobody To Love",
                "TELYKast",
                "https://itp.live/public/images/2021/03/10/screenshot20210310at61815pm.png",
                "nobody_to_love"
            )
        )
        books.add(
            BookEntity(
                "Follow You",
                "Imagine Dragons",
                "https://i1.sndcdn.com/artworks-0zjlsBvWaZNtCLTX-sKy5QA-t500x500.jpg",
                "follow_you"
            )
        )
        return books
    }

}