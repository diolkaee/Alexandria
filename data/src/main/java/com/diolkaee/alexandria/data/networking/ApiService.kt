package com.diolkaee.alexandria.data.networking

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * [API documentation](https://openlibrary.org/developers/api)
 */
interface ApiService {

    @GET("books")
    suspend fun getBooks(
        @Query("bibkeys") searchTerm: String,
        @Query("jscmd") resolution: String,
        @Query("format") dataFormat: String
    ): BookListData

    companion object {
        const val BASE_URL = "https://openlibrary.org/api/"
    }
}

suspend fun ApiService.retrieveBooks(isbn13: Long) = getBooks("ISBN:$isbn13", "data", "json").results
