package com.maandraj.catalog.ui.catalog

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.maandraj.catalog.data.model.Catalog
import com.maandraj.catalog.di.catalog.CatalogComponent
import com.maandraj.catalog.di.catalog.CatalogDepsProvider
import com.maandraj.catalog.di.catalog.DaggerCatalogComponent

import com.maandraj.catalog.domain.interactor.CatalogInteractor
import com.maandraj.core.result.ErrorType
import com.maandraj.ext.SingleLiveEvent
import com.maandraj.ext.asLiveData
import com.maandraj.ext.doIfFailure
import com.maandraj.ext.doIfSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class CatalogViewModel @Inject constructor(
    private val catalogInteractor: CatalogInteractor,
) : ViewModel() {

    private val _catalog = MutableLiveData<Catalog>()
    val catalog = _catalog.asLiveData()

    private val _loading = SingleLiveEvent<Boolean>()
    val loading = _loading.asLiveData()

    private val _snackbarError = SingleLiveEvent<ErrorType>()
    val snackbarError = _snackbarError.asLiveData()

    fun getCatalog() = viewModelScope.launch {
        _loading.postValue(true)
        val result = catalogInteractor.getCatalog()
        result.doIfSuccess {
            _catalog.postValue(it)
        }
        result.doIfFailure { error, ex ->
            _snackbarError.postValue(error)
            Log.e(TAG, ex?.message.toString())
        }
        _loading.postValue(false)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val catalogInteractor: Provider<CatalogInteractor>,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == CatalogViewModel::class.java)
            return CatalogViewModel(catalogInteractor.get()) as T
        }
    }
}

class CatalogComponentViewModel : ViewModel() {
    val catalogComponent: CatalogComponent =
        DaggerCatalogComponent.builder().catalogDeps(CatalogDepsProvider.deps).build()
}

private const val TAG = "CatalogVM"