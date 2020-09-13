package com.kotlin.gojek.assignment.dagger.module

import com.kotlin.gojek.assignment.dagger.provider.GitHubRepoFragmentProvider
import com.kotlin.gojek.assignment.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityModule {
    @ContributesAndroidInjector(
        modules = [
            GitHubRepoFragmentProvider::class
        ]
    )
    fun mainActivityInjector(): MainActivity
}