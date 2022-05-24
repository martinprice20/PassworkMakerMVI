package com.martinprice20.passwordmakermvi.di.module

import androidx.lifecycle.ViewModel
import com.martinprice20.passwordmakermvi.PasswordMakerViewModel
import com.martinprice20.passwordmakermvi.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PasswordMakerViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PasswordMakerViewModel::class)
    abstract fun bindPasswordViewModel(passwordMakerViewModel: PasswordMakerViewModel): ViewModel
}