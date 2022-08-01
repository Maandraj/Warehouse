package com.maandraj.catalog.data.mapper

import com.maandraj.catalog.data.model.Catalog
import com.maandraj.core.mapper.BaseMapper
import javax.inject.Inject

/**
 * Мапит данные из сервера в понятный вид
 */
class CatalogMapperImpl @Inject constructor(
    private val productMapperImpl: ProductMapperImpl,
) : BaseMapper<List<String>, Catalog> {
    override fun map(res: List<String>): Catalog {
        val products = productMapperImpl.map(res)
        return Catalog(products)
    }
}