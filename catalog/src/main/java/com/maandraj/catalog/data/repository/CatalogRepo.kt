package com.maandraj.catalog.data.repository

import com.maandraj.catalog.data.model.Catalog
import com.maandraj.core.result.ResultOf

interface CatalogRepo {
    suspend fun getCatalog(): ResultOf<Catalog>
}