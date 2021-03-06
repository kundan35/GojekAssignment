package com.kotlin.gojek.assignment.dagger.builder

import androidx.lifecycle.ViewModelProvider
import com.kotlin.gojek.assignment.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [(ViewModelBuilder::class)])
abstract class ViewModelFactoryBuilder {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}