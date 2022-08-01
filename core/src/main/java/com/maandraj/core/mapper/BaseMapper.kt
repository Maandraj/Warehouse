package com.maandraj.core.mapper

interface BaseMapper<in A, out B> {
    fun map(res: A): B
}