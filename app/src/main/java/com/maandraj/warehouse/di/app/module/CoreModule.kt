package com.maandraj.warehouse.di.app.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {
    @Singleton
    @Provides
    fun provideMoshi() : Moshi = Moshi.Builder().build()
}