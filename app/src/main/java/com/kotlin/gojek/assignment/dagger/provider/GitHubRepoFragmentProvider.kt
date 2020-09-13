package com.kotlin.gojek.assignment.dagger.provider

import com.kotlin.gojek.assignment.presentation.trending.GitHubRepoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface GitHubRepoFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideMoviesFragment(): GitHubRepoFragment;
}