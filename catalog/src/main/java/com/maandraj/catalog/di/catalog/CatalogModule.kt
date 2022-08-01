package com.maandraj.catalog.di.catalog

import com.maandraj.catalog.data.api.CatalogApiService
import com.maandraj.catalog.data.mapper.CatalogMapperImpl
import com.maandraj.catalog.data.repository.CatalogRepo
import com.maandraj.catalog.data.repository.CatalogRepoImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CatalogModule {
    @Provides
    @CatalogScope
    fun provideCatalogService(retrofit: Retrofit): CatalogApiService =
        retrofit.create(CatalogApiService::class.java)

    @Provides
    @CatalogScope
    fun provideCatalogRepoImpl(
        catalogApiService: CatalogApiService,
        catalogMapperImpl: CatalogMapperImpl,
    ): CatalogRepo =
        CatalogRepoImpl(
            catalogApiService = catalogApiService,
            catalogMapperImpl = catalogMapperImpl)
}