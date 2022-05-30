@file: Suppress("ConstructorParameterNaming")

package com.diolkaee.alexandria.data.networking

import com.squareup.moshi.JsonClass

data class BookListData(val results: List<BookData>)

@JsonClass(generateAdapter = true)
data class BookData(
    val publishers: List<PublisherData>,
    val title: String,
    val number_of_pages: Int?,
    val authors: List<AuthorData>,
    val publish_date: String,
    val identifiers: IdentifierData,
    val cover: CoverData?,
)

@JsonClass(generateAdapter = true)
data class PublisherData(val name: String)

@JsonClass(generateAdapter = true)
data class AuthorData(val name: String)

@JsonClass(generateAdapter = true)
data class CoverData(val small: String, val medium: String, val large: String)

@JsonClass(generateAdapter = true)
data class IdentifierData(val isbn_13: List<String>?, val isbn_10: List<String>?)
