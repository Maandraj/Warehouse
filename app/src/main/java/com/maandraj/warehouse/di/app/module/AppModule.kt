package com.maandraj.warehouse.di.app.module

import dagger.Module

@Module(includes = [
    NetworkModule::class,
    CoreModule::class
])
class AppModule