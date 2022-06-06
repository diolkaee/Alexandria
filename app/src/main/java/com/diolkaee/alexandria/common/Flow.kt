package com.diolkaee.alexandria.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * Allows LiveData to be filtered with an observable filter.
 * This is useful for e.g. filtering a list when searching.
 */
fun <T> LiveData<List<T>>.filter(filter: LiveData<(T?) -> Boolean>): LiveData<List<T>> {
    val mediator = MediatorLiveData<List<T>>()

    mediator.addSource(this) {
        mediator.value = it.filter(filter.value ?: { true })
    }
    mediator.addSource(filter) {
        mediator.value = this.value?.filter(it)
    }
    return mediator
}

inline fun <T : Any, R : Comparable<R>> LiveData<List<T>>.sortBy(crossinline selector: (T) -> R?): LiveData<List<T>> {
    val mediator = MediatorLiveData<List<T>>()

    mediator.addSource(this) {
        mediator.value = it.sortedBy(selector)
    }
    return mediator
}

