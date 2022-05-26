package com.martinprice20.passwordmakermvi.di.component

import com.martinprice20.passwordmakermvi.MainActivity
import com.martinprice20.passwordmakermvi.di.module.PasswordMakerViewModelModule
import com.martinprice20.passwordmakermvi.di.annotation.PerActivity
import com.martinprice20.passwordmakermvi.di.module.ViewModelFactoryModule
import com.martinprice20.passwordmakermvi.views.number.NumberFragment
import com.martinprice20.passwordmakermvi.views.symbol.SymbolFragment
import com.martinprice20.passwordmakermvi.views.word.WordFragment
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ViewModelFactoryModule::class, PasswordMakerViewModelModule::class])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(numberFragment: NumberFragment)
    fun inject(wordFragment: WordFragment)
    fun inject(symbolFragment: SymbolFragment)
}