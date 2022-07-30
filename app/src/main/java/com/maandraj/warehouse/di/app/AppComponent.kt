package com.maandraj.warehouse.di.app

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@AppScope
@Component(modules = [])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Context): Builder
        fun build(): AppComponent
    }
}
@Scope
annotation class AppScope