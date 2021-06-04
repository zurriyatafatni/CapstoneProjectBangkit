package com.djevannn.capstoneproject.data.source.remote


import com.djevannn.capstoneproject.data.source.remote.response.BookResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.io.File

interface ApiService {
    @GET("")
    fun getBookList(): List<BookResponse>

    @POST("")
    @FormUrlEncoded
    fun postBook(
        @Field("file") file: File,
    )
}