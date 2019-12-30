package com.boni.breakingbadfacts.base

import androidx.lifecycle.MutableLiveData

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}

fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        action.invoke(data)
    }
    return this
}

fun <T : Any> Result<T>.onError(action: (Throwable) -> Unit): Result<T> {
    if (this is Result.Error) {
        action.invoke(exception)
    }
    return this
}

fun <T : Any> Result<T>.notifyError(liveData: MutableLiveData<ErrorState>): Result<T> {
    if (this is Result.Error) {
        liveData.postValue(ErrorState(message = exception.message))
    }
    return this
}

fun <T : Any> Result<T>.getOrThrow(): T {
    if (this is Result.Error) {
        throw Throwable(exception)
    } else {
        return (this as Result.Success).data
    }
}

fun <T: Any> Result<T>.isSuccess(): Boolean {
    return this is Result.Success
}

fun <T: Any> Result<T>.get(): T {
    return (this as Result.Success).data
}

