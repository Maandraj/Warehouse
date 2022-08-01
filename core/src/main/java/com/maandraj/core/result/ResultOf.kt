package com.maandraj.core.result

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R): ResultOf<R>()
    data class Failure(
        val errorType : ErrorType,
        val exception: Throwable? = null
    ): ResultOf<Nothing>()
}