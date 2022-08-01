package com.maandraj.catalog.di.catalog

import android.content.Context
import androidx.annotation.RestrictTo
import com.maandraj.catalog.ui.catalog.CatalogFragment
import com.squareup.moshi.Moshi
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Scope
import kotlin.properties.Delegates

@CatalogScope
@Component(modules = [CatalogModule::class],
    dependencies = [CatalogDeps::class])
interface CatalogComponent {
    fun inject(catalogFragment: CatalogFragment)

    @Component.Builder
    interface Builder {
        fun catalogDeps(catalogDeps: CatalogDeps): Builder
        fun build(): CatalogComponent
    }
}

interface CatalogDeps {
    val retrofit: Retrofit
    val moshi: Moshi
    val applicationContext: Context
}

interface CatalogDepsProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: CatalogDeps

    companion object : CatalogDepsProvider by CatalogDepsStore
}

object CatalogDepsStore : CatalogDepsProvider {
    override var deps: CatalogDeps by Delegates.notNull()
}

@Scope
annotation class CatalogScope