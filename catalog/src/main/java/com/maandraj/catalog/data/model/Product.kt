package com.maandraj.catalog.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "code")
    val code: Long,
    @Json(name = "barcode")
    val barcode: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "receiptText")
    val receiptText: String,
    @Json(name = "price")
    val price: Float,
    @Json(name = "count")
    val count: Float,
    @Json(name = "alcohol")
    val alcohol: Alcohol?,
) {
    fun compareCode(code: Long): Boolean = this.code == code
}
