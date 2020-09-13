package com.kotlin.gojek.assignment.dagger.builder

import androidx.lifecycle.ViewModel
import com.kotlin.gojek.assignment.dagger.ViewModelKey
import com.kotlin.gojek.assignment.presentation.trending.GitHubReposViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(GitHubReposViewModel::class)
    abstract fun provideGitHubReposFragmentViewModel(gitHubReposViewModel: GitHubReposViewModel): ViewModel
}