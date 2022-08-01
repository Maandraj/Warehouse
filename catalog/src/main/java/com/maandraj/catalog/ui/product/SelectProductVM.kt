package com.maandraj.catalog.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maandraj.catalog.di.product.DaggerSelectProductComponent
import com.maandraj.catalog.di.product.SelectProductComponent
import com.maandraj.catalog.di.product.SelectProductDepsStore
import javax.inject.Inject

class SelectProductVM @Inject constructor(
) : ViewModel() {

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == SelectProductVM::class.java)
            return SelectProductVM() as T
        }
    }
}

class SelectProductVMComponentViewModel : ViewModel() {
    val selectProductComponent: SelectProductComponent =
        DaggerSelectProductComponent.builder().selectProduct(SelectProductDepsStore.deps).build()
}

private const val TAG = "SelectProductVM"