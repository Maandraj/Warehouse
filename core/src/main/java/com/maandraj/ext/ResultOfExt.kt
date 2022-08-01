package com.maandraj.ext

import com.maandraj.core.result.ErrorType
import com.maandraj.core.result.ResultOf

inline fun <reified T> ResultOf<T>.doIfFailure(callback: (errorType: ErrorType, exception: Throwable?) -> Unit) {
    if (this is ResultOf.Failure) {
        callback(errorType, exception)
    }
}

inline fun <reified T> ResultOf<T>.doIfSuccess(callback: (value: T) -> Unit) {
    if (this is ResultOf.Success) {
        callback(value)
    }
}