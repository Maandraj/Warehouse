package com.maandraj.core.result

sealed interface ErrorType

object ProductsNotFound : ErrorType
object InternetError : ErrorType