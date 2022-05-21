package com.martinprice20.passwordmakermvi.di

import com.martinprice20.passwordmakermvi.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}