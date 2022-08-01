package com.maandraj.catalog.di.product

import androidx.annotation.RestrictTo
import com.maandraj.catalog.ui.product.SelectProductFragment
import com.squareup.moshi.Moshi
import dagger.Component
import javax.inject.Scope
import kotlin.properties.Delegates

@SelectProductScope
@Component(dependencies = [SelectProductDeps::class])
interface SelectProductComponent {
    fun inject(selectProductFragment: SelectProductFragment)

    @Component.Builder
    interface Builder {
        fun selectProduct(selectProductDeps: SelectProductDeps): Builder
        fun build(): SelectProductComponent
    }
}

interface SelectProductDeps {
    val moshi: Moshi
}

interface SelectProductDepsProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: SelectProductDeps

    companion object : SelectProductDepsProvider by SelectProductDepsStore
}

object SelectProductDepsStore : SelectProductDepsProvider {
    override var deps: SelectProductDeps by Delegates.notNull()
}

@Scope
annotation class SelectProductScope