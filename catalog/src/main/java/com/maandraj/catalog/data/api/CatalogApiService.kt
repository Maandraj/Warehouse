package com.maandraj.catalog.data.api


import retrofit2.Call
import retrofit2.http.GET

interface CatalogApiService {
    @GET("test/catalog.spr")
    fun getCatalog(): Call<String>
}