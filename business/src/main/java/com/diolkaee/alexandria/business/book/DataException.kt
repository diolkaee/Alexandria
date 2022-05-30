package com.diolkaee.alexandria.business.book

sealed class DataException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    object NotFound : DataException()
}
