package com.kotlin.gojek.assignment

import android.app.Activity
import android.app.Application
import javax.inject.Inject

class MainApplication : Application(), HasActivityInjector{
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

    }

    override fun activityInjector() = activityInjector
}