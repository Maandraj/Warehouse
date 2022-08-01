package com.maandraj.catalog.data.mapper

import com.maandraj.catalog.data.mapper.enum.AlcoholAds

interface AlcoholMapper<in A, out B> {
    fun map(res: A, filterIds: Array<AlcoholAds>, codeProduct: Long, regex: Regex): B
}