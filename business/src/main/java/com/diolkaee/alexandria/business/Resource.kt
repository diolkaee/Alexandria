package com.diolkaee.alexandria.business

import kotlinx.coroutines.flow.MutableSharedFlow

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val cause: Exception) : Resource<T>()
}

internal suspend fun <T> MutableSharedFlow<Resource<T>>.loading() = emit(Resource.Loading())
internal suspend fun <T> MutableSharedFlow<Resource<T>>.success(data: T) = emit(Resource.Success(data))
internal suspend fun <T> MutableSharedFlow<Resource<T>>.error(cause: Exception) = emit(Resource.Error(cause))
