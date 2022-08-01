package com.maandraj.warehouse

import android.app.Application
import com.maandraj.catalog.di.catalog.CatalogDepsStore
import com.maandraj.catalog.di.product.SelectProductDepsStore
import com.maandraj.warehouse.di.app.AppComponent
import com.maandraj.warehouse.di.app.DaggerAppComponent

class App : Application() {
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        CatalogDepsStore.deps = appComponent
        SelectProductDepsStore.deps = appComponent
    }
}