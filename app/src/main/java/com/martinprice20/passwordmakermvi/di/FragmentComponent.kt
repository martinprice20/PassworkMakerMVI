package com.martinprice20.passwordmakermvi.di

import com.martinprice20.passwordmakermvi.home.HomeFragment
import com.martinprice20.passwordmakermvi.number.NumberFragment
import com.martinprice20.passwordmakermvi.result.ResultFragment
import com.martinprice20.passwordmakermvi.symbol.SymbolFragment
import com.martinprice20.passwordmakermvi.word.WordFragment
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