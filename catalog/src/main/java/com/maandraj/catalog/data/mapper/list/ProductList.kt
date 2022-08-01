package com.maandraj.catalog.data.mapper.list

import com.maandraj.catalog.data.model.Alcohol
import com.maandraj.catalog.data.model.Product

class ProductList : ArrayList<Product>() {
    fun addAlcohol(item: Product, alcohol: Alcohol?) {
        alcohol ?: return
        val index = this.indexOf(item)
        this[index] = item.copy(alcohol = alcohol)
    }
}