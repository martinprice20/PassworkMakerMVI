package com.martinprice20.passwordmakermvi.di

import com.martinprice20.passwordmakermvi.PMmviApp
import com.martinprice20.passwordmakermvi.utils.ResourceProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ResourceModule::class])
interface AppComponent {

    fun provideResources(): ResourceProvider

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun setApplication(application: PMmviApp): Builder

    }
}