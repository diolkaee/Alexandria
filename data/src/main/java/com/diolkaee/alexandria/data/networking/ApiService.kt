package com.diolkaee.alexandria.data.networking

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("books")
    suspend fun getBooks(
        @Query("bibkeys") searchTerm: String,
        @Query("jscmd") resolution: String,
        @Query("format") dataFormat: String
    ): BookListData
}

// TODO return list
suspend fun ApiService.retrieveBook(isbn13: String) = getBooks("ISBN:$isbn13", "data", "json").results.firstOrNull()
