package com.maandraj.catalog.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Alcohol(
    @Json(name = "type")
    val type: Int?,
    @Json(name = "percent")
    val percent: Float,
)