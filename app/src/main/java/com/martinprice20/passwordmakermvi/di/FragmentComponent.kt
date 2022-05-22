package com.martinprice20.passwordmakermvi.di

import com.martinprice20.passwordmakermvi.views.home.HomeFragment
import com.martinprice20.passwordmakermvi.views.number.NumberFragment
import com.martinprice20.passwordmakermvi.views.result.ResultFragment
import com.martinprice20.passwordmakermvi.views.symbol.SymbolFragment
import com.martinprice20.passwordmakermvi.views.word.WordFragment
import dagger.Component

@PerFragment
@Component(dependencies = [AppComponent::class])
interface FragmentComponent {

    fun inject(homeFragment: HomeFragment)
    fun inject(numberFragment: NumberFragment)
    fun inject(resultFragment: ResultFragment)
    fun inject(wordFragment: WordFragment)
    fun inject(symbolFragment: SymbolFragment)
}