@file: Suppress("ConstructorParameterNaming")

package com.diolkaee.alexandria.data.networking

import com.squareup.moshi.JsonClass

data class BookListData(val results: List<BookData>)

@JsonClass(generateAdapter = true)
data class BookData(
    @Transient
    val isbn: String? = null, // Transient requires null, this will be nonnull after deserialization
    val publishers: List<PublisherData>,
    val title: String,
    val number_of_pages: Int?,
    val authors: List<AuthorData>,
    val publish_date: String,
    val cover: CoverData?,
)

@JsonClass(generateAdapter = true)
data class PublisherData(val name: String)

@JsonClass(generateAdapter = true)
data class AuthorData(val name: String)

@JsonClass(generateAdapter = true)
data class CoverData(val small: String, val medium: String, val large: String)
