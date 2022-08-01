package com.maandraj.catalog.data.mapper.list

class ResponseList : ArrayList<String>() {
    fun getCode(): Long = this[0].toLong()
    fun getBarcode(): String = this[1]
    fun getName(): String = this[2]
    fun getReceiptText(): String = this[3]
    fun getPrice(): Float = this[4].toFloat()
    fun getCount(): Float = this[5].toFloat()
}
