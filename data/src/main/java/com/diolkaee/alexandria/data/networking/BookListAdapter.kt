package com.diolkaee.alexandria.data.networking

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson

/**
 * The OpenLibrary api returns a map of <ISBN, BookData> which we manually need to convert
 * into a list of BookData.
 */
class BookListAdapter {

    @FromJson
    fun fromJson(
        jsonReader: JsonReader,
        bookDelegate: JsonAdapter<BookData>,
    ): BookListData {
        val data = mutableListOf<BookData>()

        jsonReader.beginObject()
        while (jsonReader.hasNext()) {
            // Skip unknown key name and proceed to nested object
            val nextIdentifier = jsonReader.nextName()
            val nextBook = bookDelegate.fromJson(jsonReader.nextSource().readUtf8())
            if (nextBook != null) {
                // API returns identifier in the format "ISBN:<ISBN13>"
                val normalizedIdentifier = nextIdentifier.substringAfter(":")
                data.add(nextBook.copy(isbn = normalizedIdentifier))
            }
        }
        jsonReader.endObject()

        return BookListData(data)
    }

    @ToJson
    @Suppress("UnusedPrivateMember")
    fun toJson(element: BookListData): String {
        throw NotImplementedError("Serializing BookListData is not supported.")
    }
}
