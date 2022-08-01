package com.maandraj.warehouse.di.app

import android.content.Context
import com.maandraj.catalog.di.catalog.CatalogDeps
import com.maandraj.catalog.di.product.SelectProductDeps
import com.maandraj.warehouse.di.app.module.AppModule
import com.squareup.moshi.Moshi
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : CatalogDeps, SelectProductDeps {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Context): Builder
        fun build(): AppComponent
    }

    override val retrofit: Retrofit
    override val moshi: Moshi
}
