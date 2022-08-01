package com.maandraj.core.result

sealed interface ErrorType

object ProductsNotFound : ErrorType
object ProductsGetError : ErrorType
object InternetError : ErrorType