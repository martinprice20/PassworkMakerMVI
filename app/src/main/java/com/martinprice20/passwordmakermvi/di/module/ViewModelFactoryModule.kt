package com.martinprice20.passwordmakermvi.di.module

import androidx.lifecycle.ViewModelProvider
import com.martinprice20.passwordmakermvi.di.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}