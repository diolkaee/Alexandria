package com.diolkaee.alexandria.data.networking

import retrofit2.http.GET
import retrofit2.http.Query

const val API_BASE_URL = "https://openlibrary.org/api/"

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
}

suspend fun ApiService.retrieveBooks(isbn13: Long) = getBooks("ISBN:$isbn13", "data", "json").results
