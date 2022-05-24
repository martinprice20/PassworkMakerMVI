package com.martinprice20.passwordmakermvi.di.component

import dagger.Subcomponent

@Subcomponent()
interface FragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }

}