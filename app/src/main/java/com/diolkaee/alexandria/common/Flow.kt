package com.diolkaee.alexandria.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

/**
 * Filter a flow with an observable filter predicate.
 * This is useful for e.g. filtering a list when searching.
 */
fun <T : Any> Flow<List<T>>.flowFilter(predicate: Flow<(T) -> Boolean>) =
    this.combine(predicate) { bookList, filter ->
        bookList.filter(filter)
    }

fun <T : Any, R : Comparable<R>> Flow<List<T>>.sortBy(selector: (T) -> R?) =
    this.map { it.sortedBy(selector) }
