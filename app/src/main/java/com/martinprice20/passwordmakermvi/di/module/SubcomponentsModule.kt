package com.martinprice20.passwordmakermvi.di.module

import com.martinprice20.passwordmakermvi.di.component.ActivityComponent
import com.martinprice20.passwordmakermvi.di.component.FragmentComponent
import dagger.Module

@Module(subcomponents = [ActivityComponent::class, FragmentComponent::class])
class SubcomponentsModule {
}