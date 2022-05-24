package com.martinprice20.passwordmakermvi.di.module

import com.martinprice20.passwordmakermvi.PMmviApp
import com.martinprice20.passwordmakermvi.utils.AndroidResourceProvider
import com.martinprice20.passwordmakermvi.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ResourceModule() {

    @Provides
    @Singleton
    fun providesResources(app: PMmviApp): ResourceProvider {
        return AndroidResourceProvider(app)
    }
}