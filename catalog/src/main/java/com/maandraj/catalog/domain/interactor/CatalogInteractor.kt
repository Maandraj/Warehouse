package com.maandraj.catalog.domain.interactor

import com.maandraj.catalog.data.repository.CatalogRepo
import javax.inject.Inject

class CatalogInteractor @Inject constructor(
    private val catalogRepo: CatalogRepo,
) {
    suspend fun getCatalog() = catalogRepo.getCatalog()
}