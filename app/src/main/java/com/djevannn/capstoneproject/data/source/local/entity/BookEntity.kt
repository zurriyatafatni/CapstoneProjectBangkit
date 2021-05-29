package com.djevannn.capstoneproject.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_entities")
data class BookEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "author")
        val author: String,

        @ColumnInfo(name = "image")
        val image: String,

        @ColumnInfo(name = "url")
        val url: String
)