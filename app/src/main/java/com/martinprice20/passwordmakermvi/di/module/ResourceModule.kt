package com.martinprice20.passwordmakermvi.di.module

import com.martinprice20.passwordmakermvi.PMmviApp
import com.martinprice20.passwordmakermvi.utils.AndroidResourceAndUtilsProvider
import com.martinprice20.passwordmakermvi.utils.ResourceAndUtilsProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ResourceModule() {

    @Provides
    @Singleton
    fun providesResources(app: PMmviApp): ResourceAndUtilsProvider {
        return AndroidResourceAndUtilsProvider(app)
    }
}