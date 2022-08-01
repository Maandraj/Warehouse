package com.maandraj.catalog.data.repository

import com.maandraj.catalog.data.api.CatalogApiService
import com.maandraj.catalog.data.mapper.CatalogMapperImpl
import com.maandraj.catalog.data.model.Catalog
import com.maandraj.core.result.InternetError
import com.maandraj.core.result.ProductsGetError
import com.maandraj.core.result.ProductsNotFound
import com.maandraj.core.result.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CatalogRepoImpl @Inject constructor(
    private val catalogApiService: CatalogApiService,
    private val catalogMapperImpl: CatalogMapperImpl,
) : CatalogRepo {
    override suspend fun getCatalog(): ResultOf<Catalog> {
        try {
            val result = suspendCoroutine<List<String>?> { continuation ->
                val catalogRes = catalogApiService.getCatalog()
                catalogRes.enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        response.body()?.trim()?.split("\n")?.let {
                            continuation.resumeWith(Result.success(it))
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                })
            }
            return withContext(Dispatchers.IO) {
                if (result != null) {
                    val catalog = catalogMapperImpl.map(result)
                    ResultOf.Success(catalog)
                } else {
                    ResultOf.Failure(ProductsNotFound)
                }
            }
        } catch (ex: UnknownHostException) {
            return ResultOf.Failure(InternetError, ex)
        } catch (t: Throwable) {
            return ResultOf.Failure(ProductsGetError, t)
        }
    }
}