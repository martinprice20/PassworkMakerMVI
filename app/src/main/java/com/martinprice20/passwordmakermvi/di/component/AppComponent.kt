package com.martinprice20.passwordmakermvi.di.component

import com.martinprice20.passwordmakermvi.PMmviApp
import com.martinprice20.passwordmakermvi.di.module.RepositoryModule
import com.martinprice20.passwordmakermvi.di.module.ResourceModule
import com.martinprice20.passwordmakermvi.di.module.SubcomponentsModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ResourceModule::class, RepositoryModule::class, SubcomponentsModule::class])
interface AppComponent {

    fun activityComponent(): ActivityComponent.Factory
    fun fragmentComponent(): FragmentComponent.Factory

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun setApplication(application: PMmviApp): Builder

    }
}