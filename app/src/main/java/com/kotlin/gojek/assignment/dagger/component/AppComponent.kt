package com.kotlin.gojek.assignment.dagger.component

import android.app.Application
import com.kotlin.gojek.assignment.MainApplication
import com.kotlin.gojek.assignment.dagger.module.ActivityModule
import com.kotlin.gojek.assignment.dagger.module.ApplicationModule
import com.kotlin.gojek.assignment.dagger.module.DatabaseModule
import com.kotlin.gojek.assignment.dagger.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityModule::class, ApplicationModule::class, NetworkModule::class, DatabaseModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MainApplication)
}